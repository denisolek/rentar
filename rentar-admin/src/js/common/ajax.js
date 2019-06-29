import axios from 'axios';
import qs from 'qs';
import Utils from './utils';

const DefaultParam = {repeatable: false};
const TargetHost = 'https://api.rentar.eu';

let ajax = {
  requestingApi: new Set(),
  extractUrl: function (url) {
    return url ? url.split('?')[0] : '';
  },
  isRequesting: function (url) {
    let api = this.extractUrl(url);
    return this.requestingApi.has(api);
  },
  addRequest: function (url) {
    let api = this.extractUrl(url);
    this.requestingApi.add(api);
  },
  deleteRequest: function (url) {
    let api = this.extractUrl(url);
    this.requestingApi.delete(api);
  },
  get: function (url, param, extendParam) {
    let params = {
      url,
      method: 'GET'
    };
    if (param) {
      ;
      params.params = param;
    }
    return this.ajax(params, extendParam);
  },
  post: function (url, param, extendParam) {
    var params = {
      url,
      method: 'POST'
    };
    if (param) params.data = qs.stringify(param);
    return this.ajax(params, extendParam);
  },
  postJson: function (url, paramJson, extendParam) {
    return this.ajax({
      url,
      method: 'POST',
      data: paramJson
    }, extendParam);
  },
  patchJson: function (url, paramJson, dataType, extendParam) {
    return this.ajax({
      url,
      method: 'PATCH',
      data: paramJson
    }, extendParam);
  },
  delete: function (url, extendParam) {
    return this.ajax({
      url: url,
      method: 'DELETE'
    }, extendParam);
  },
  ajax: function (param, extendParam) {
    let params = Utils.extend({}, DefaultParam, param, extendParam || {});
    params.crossDomain = params.url.indexOf('http') === 0;
    params.url = TargetHost + params.url;
    let url = params.url;
    if (params.method != 'GET') {
      if (this.isRequesting(url)) {
        return new Promise((resolve, reject) => {
          resolve({ok: false, msg: 'Repeat request'});
        });
      }
      if (params.repeatable === false) {
        this.addRequest(url);
      }
    }
    let header = {};
    let defaultParam = {
      headers: header,
      responseType: 'json',
      validateStatus: function (status) {
        return true;
      },
      paramsSerializer: (params) => {
        return qs.stringify(params, {allowDots: true});
      }
    };
    if (params.crossDomain) {
      defaultParam.headers = {};
    }
    let that = this;
    params = Utils.extend({}, defaultParam, params);
    return new Promise((resolve) => {
      return axios.request(params).then((response) => {
        that.deleteRequest(params.url);
        let data = response.data;
        let status = response.status;
        if (status !== 200 && status !== 201) {
          let errorMessage = data.message || 'Coś poszło nie tak!';
          HeyUI.$Message.error(errorMessage + ' [' + status + ']');
        }
        resolve({data: data, status: status});
      }).catch((error) => {
        that.deleteRequest(params.url);
        resolve(error);
      });
    });
  }
};
export default ajax;
