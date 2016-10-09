const express = require('express');
const router = express.Router();
const quoteApi = require('./build/quotes.js');

router.get('/api/quotes/random', quoteApi.getRandom);

module.exports = router;
