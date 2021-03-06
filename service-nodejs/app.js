/*
 * JBoss, Home of Professional Open Source
 * Copyright 2016, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

var express = require('express');
var session = require('express-session');
var bodyParser = require('body-parser');
var Keycloak = require('keycloak-connect');
var hogan = require('hogan-express');
var cors = require('cors');

var app = express();

var server = app.listen(3000, function () {
  var host = server.address().address;
  var port = server.address().port;
  console.log('Example app listening at http://%s:%s', host, port);
});

// Register '.mustache' extension with The Mustache Express
app.set('view engine', 'html');
app.set('views', require('path').join(__dirname, '/view'));
app.engine('html', hogan);

app.use(bodyParser.json());

// Enable CORS support
app.use(cors());

// Provide the session store to the Keycloak so that sessions
// can be invalidated from the Keycloak console callback.
//
// Additional configuration is read from keycloak.json file
// installed from the Keycloak web console.
var memoryStore = new session.MemoryStore();

var keycloak = new Keycloak({
  store: memoryStore,
  scope: 'offline_access' 
});
// Create a session-store to be used by both the express-session
// middleware and the keycloak middleware.


app.use(keycloak.middleware({
  logout: '/logout',
  admin: '/'
}));

app.use(session({
  secret: 'mySecret',
  resave: false,
  saveUninitialized: true,
  store: memoryStore
}));

app.get('/', function (req, res) {
  res.render('index');
});

// app.get('/login', keycloak.protect(), function (req, res) {
//   res.render('index', {
//     result: JSON.stringify(JSON.parse(req.session['keycloak-token']), null, 4),
//     event: '1. Authentication\n2. Login'
//   });
// });

app.use('*', function (req, res) {
  res.send('Not found!');
});
