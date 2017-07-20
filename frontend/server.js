#!/usr/bin/env node

'use strict';

const path = require('path')
const express = require('express')
const favicon = require('serve-favicon')
const webpack = require('webpack')
const webpackDevMiddleware = require('webpack-dev-middleware')
const webpackHotMiddleware = require('webpack-hot-middleware');

const isDev = process.env.NODE_ENV !== 'production'
const port = isDev ? 8000 : process.env.PORT
const app = express()
const configFavicon = app => app.use(favicon(path.join(__dirname, 'src/assets/images/favicon.ico')));

if (isDev) {
    const devConfig = require('./webpack.dev.config.js');

    const compiler = webpack(devConfig);
    const devMiddleware = webpackDevMiddleware(compiler, {
        publicPath: devConfig.output.publicPath,
        contentBase: 'src',
        stats: {
            colors: true,
            hash: false,
            timings: true,
            chunks: false,
            chunkModules: false,
            modules: false
        }
    });

    app.use(devMiddleware);
    app.use(webpackHotMiddleware(compiler));
    configFavicon(app);
    app.get('*', (req, res) => {
        res.write(devMiddleware.fileSystem.readFileSync(path.join(__dirname, 'dist/index.html')));
        res.end();
    })
} else {
    app.use(express.static(__dirname + '/dist'));
    configFavicon(app);
    app.get('*', (req, res) => {
        const match = req.url.match(/^\/assets\/.+\.(css|js|jpeg|jpg|png|gif)$/)[0];
        if (match != null) {
            res.sendFile(path.join(__dirname, 'dist', match.replace(/^\/assets\//, '')));
        } else {
            res.sendFile(path.join(__dirname, 'dist/index.html'));
        }
    })
}

app.listen(port, '0.0.0.0', err => {
    if (err) {
        console.log(err);
    }
    console.info('==> Listening on port %s. Open up http://0.0.0.0:%s/ in your browser.', port, port)
});