/*eslint no-console:0 */
'use strict';

require('dotenv').config();
const express = require('express');
const config = require('./config');
const app = express();
const port = config.port;

app.use(function(req, res, next) {
  res.header("Access-Control-Allow-Origin", "*");
  res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
  next();
});

app.use(require('./routes'));

var server = app.listen(port, () => {
  var host = server.address().address;
  var port = server.address().port;
  console.log('Application listening at http://%s:%s', host, port);
});
