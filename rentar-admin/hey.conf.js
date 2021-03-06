const path = require('path');

module.exports = {
  port: 9012,
  root: 'dist',
  webpack: {
    console: true,
    publicPath: '/',
    output: {
      './index.html': {
        entry: './src/app'
      }
    },
    alias: {
      model: './src/js/model/',
      js: './src/js/',
      components: './src/components/'
    },
    global: {
      Utils: [path.resolve(__dirname, 'src/js/common/utils'), 'default'],
      Manba: 'manba',
      HeyUI: 'heyui',
      Model: 'js-model',
      G: 'hey-global',
      log: 'hey-log',
      R: [path.resolve(__dirname, 'src/js/common/request'), 'default']
    },
    devServer: {
      historyApiFallback: true
    },
    globalVars: './src/css/var.less',
    externals: {},
    resolve: {
      root: [
        path.resolve('./src')
      ]
    }
  },
  copy: ['static/images/*', 'call/*', './baidu_verify_7O2vpVMzwg.html']
};
