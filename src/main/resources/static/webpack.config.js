var packageJSON = require('./package.json');
var path = require('path');
var webpack = require('webpack');

module.exports = {
    devtool: 'source-map',
    entry: './main.js',
    output: {
        path: path.join(__dirname, 'generated'),
        filename: 'main-generated.js'
    },
    resolve: {extensions: ['.js', '.jsx']},
    plugins: [
        new webpack.LoaderOptionsPlugin({
            debug: true
        }),
        new webpack.DefinePlugin({
            "process.env": {
                NODE_ENV: JSON.stringify("development")
            }
        })
    ],
    module: {
        rules: [
            {
                test: /\.jsx?$/,
                loader: 'babel-loader',
                exclude: /node_modules/
            },
            {
                test: /\.css$/i,
                use: ['style-loader', 'css-loader'],
            },
            {
                test: /\.less$/,
                use: [{
                    loader: "style-loader"
                }, {
                    loader: "css-loader"
                }, {
                    loader: "less-loader",
                    options: {
                        javascriptEnabled: true
                    }
                }]
            }
        ]
    },
    devServer: {
        noInfo: false,
        quiet: false,
        lazy: false,
        watchOptions: {
            poll: true
        }
    }
};
