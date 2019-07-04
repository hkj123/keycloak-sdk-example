var express = require('express');
var hogan = require('hogan-express');
var Keycloak = require('keycloak-connect');
var session = require('express-session');
const path = require('path')
var bodyParser = require('body-parser');
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

app.get('/', function (req, res) {
      res.render('index');
});

var memoryStore = new session.MemoryStore();

var keycloak = new Keycloak({
      store: memoryStore,
      scope: 'offline_access'
});

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

app.get('/login', keycloak.protect(), function (req, res) {
      res.render('index', {
            result: JSON.stringify(JSON.parse(req.session['keycloak-token']), null, 4),
            event: '1. Authentication\n2. Login'
      });
});

app.get( '/complain', keycloak.protect());