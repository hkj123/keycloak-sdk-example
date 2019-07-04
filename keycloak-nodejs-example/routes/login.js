// var express = require('express')
// var router = express.Router()
// var session = require('express-session');
// var Keycloak = require('keycloak-connect');

// var memoryStore = new session.MemoryStore();
// var keycloak = new Keycloak({ store: memoryStore });

// router.get('/', function (req, res) {
//       res.render('login', {
//             result: 'success',
//             event: '1. Authentication\n2. Login'
//       })
// })

// router.get('/protect', keycloak.protect(), function (req, res) {
//       res.render('index', {
//             result: 'protect',
//             event: 'protect'
//       });
// });

// module.exports = router