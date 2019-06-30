define(function() { return /******/ (function(modules) { // webpackBootstrap
  /******/ 	// The module cache
  /******/ 	var installedModules = {};
  /******/
  /******/ 	// The require function
  /******/ 	function __webpack_require__(moduleId) {
    /******/
    /******/ 		// Check if module is in cache
    /******/ 		if(installedModules[moduleId]) {
      /******/ 			return installedModules[moduleId].exports;
      /******/ 		}
    /******/ 		// Create a new module (and put it into the cache)
    /******/ 		var module = installedModules[moduleId] = {
      /******/ 			i: moduleId,
      /******/ 			l: false,
      /******/ 			exports: {}
      /******/ 		};
    /******/
    /******/ 		// Execute the module function
    /******/ 		modules[moduleId].call(module.exports, module, module.exports, __webpack_require__);
    /******/
    /******/ 		// Flag the module as loaded
    /******/ 		module.l = true;
    /******/
    /******/ 		// Return the exports of the module
    /******/ 		return module.exports;
    /******/ 	}
  /******/
  /******/
  /******/ 	// expose the modules object (__webpack_modules__)
  /******/ 	__webpack_require__.m = modules;
  /******/
  /******/ 	// expose the module cache
  /******/ 	__webpack_require__.c = installedModules;
  /******/
  /******/ 	// define getter function for harmony exports
  /******/ 	__webpack_require__.d = function(exports, name, getter) {
    /******/ 		if(!__webpack_require__.o(exports, name)) {
      /******/ 			Object.defineProperty(exports, name, { enumerable: true, get: getter });
      /******/ 		}
    /******/ 	};
  /******/
  /******/ 	// define __esModule on exports
  /******/ 	__webpack_require__.r = function(exports) {
    /******/ 		if(typeof Symbol !== 'undefined' && Symbol.toStringTag) {
      /******/ 			Object.defineProperty(exports, Symbol.toStringTag, { value: 'Module' });
      /******/ 		}
    /******/ 		Object.defineProperty(exports, '__esModule', { value: true });
    /******/ 	};
  /******/
  /******/ 	// create a fake namespace object
  /******/ 	// mode & 1: value is a module id, require it
  /******/ 	// mode & 2: merge all properties of value into the ns
  /******/ 	// mode & 4: return value when already ns object
  /******/ 	// mode & 8|1: behave like require
  /******/ 	__webpack_require__.t = function(value, mode) {
    /******/ 		if(mode & 1) value = __webpack_require__(value);
    /******/ 		if(mode & 8) return value;
    /******/ 		if((mode & 4) && typeof value === 'object' && value && value.__esModule) return value;
    /******/ 		var ns = Object.create(null);
    /******/ 		__webpack_require__.r(ns);
    /******/ 		Object.defineProperty(ns, 'default', { enumerable: true, value: value });
    /******/ 		if(mode & 2 && typeof value != 'string') for(var key in value) __webpack_require__.d(ns, key, function(key) { return value[key]; }.bind(null, key));
    /******/ 		return ns;
    /******/ 	};
  /******/
  /******/ 	// getDefaultExport function for compatibility with non-harmony modules
  /******/ 	__webpack_require__.n = function(module) {
    /******/ 		var getter = module && module.__esModule ?
      /******/ 			function getDefault() { return module['default']; } :
      /******/ 			function getModuleExports() { return module; };
    /******/ 		__webpack_require__.d(getter, 'a', getter);
    /******/ 		return getter;
    /******/ 	};
  /******/
  /******/ 	// Object.prototype.hasOwnProperty.call
  /******/ 	__webpack_require__.o = function(object, property) { return Object.prototype.hasOwnProperty.call(object, property); };
  /******/
  /******/ 	// __webpack_public_path__
  /******/ 	__webpack_require__.p = "";
  /******/
  /******/
  /******/ 	// Load entry module and return exports
  /******/ 	return __webpack_require__(__webpack_require__.s = 539);
  /******/ })
/************************************************************************/
/******/ ({

  /***/ 539:
  /***/ (function(module, exports, __webpack_require__) {

    module.exports = __webpack_require__(540);


    /***/ }),

  /***/ 540:
  /***/ (function(module, exports, __webpack_require__) {

    "use strict";


    Object.defineProperty(exports, "__esModule", {
      value: true
    });
    exports.default = void 0;
    var lang = {
      h: {
        locale: 'en-US',
        common: {
          cancel: 'Anuluj',
          confirm: 'Potwierdź',
          clear: 'Wyczyść',
          nullOptionText: 'proszę wybrać',
          empty: 'pueste',
          any: 'dowolne'
        },
        confirm: {
          title: 'Potwierdź'
        },
        select: {
          nullOptionText: 'proszę wybrać',
          placeholder: 'wybierz',
          emptyContent: 'brak danych',
          searchPlaceHolder: 'szukaj',
          limitSize: 'Możesz wybrać maksymalnie {0} danych.'
        },
        category: {
          placeholder: 'proszę wybrać'
        },
        cascader: {
          placeholder: 'proszę wybrać'
        },
        categoryModal: {
          limitWords: 'Możesz wybrać maksymalnie {0} danych.',
          emptyContent: 'brak danych',
          total: 'suma'
        },
        categoryPicker: {
          nullOptionText: 'proszę wybrać',
          placeholder: 'wybierz',
          total: 'suma',
          limitSize: 'Możesz wybrać maksymalnie {0} danych.'
        },
        autoComplate: {
          placeholder: 'Szukaj',
          emptyContent: 'Brak wyniku'
        },
        validation: {
          base: {
            required: ' nie może by puste',
            maxLen: " nie może mieć więcej niż {value} znaków",
            minLen: " nie może mieć mniej niż {value} znaków",
            max: ' nie więcej niż {value}',
            min: ' nie mniej niż {value}'
          },
          type: {
            int: ' nie jest liczbą całkowitą',
            number: ' nie jest cyfrą',
            email: ' nie jest poprawnym adresem email',
            url: ' nie jest poprawnym adresem URL',
            tel: ' nie jest poprawnym numerem telefonu',
            mobile: ' nie jest poprawnym numerem telefonu',
            globalmobile: ' nie jest w poprawnym numerem telefonu'
          }
        },
        date: {
          today: 'Dzisiaj',
          yesterday: 'Wczoraj',
          year: 'rok',
          month: 'miesiąc',
          week: 'tydzień',
          quarter: 'quarter',
          day: 'dzień',
          header: {
            year: '',
            month: '',
            day: ''
          },
          show: {
            week: '{year} {weeknum}th week {daystart} - {dayend}',
            weekInput: '{year} {week}th week',
            quarter: '{year} {quarter}th quarter'
          },
          months: {
            january: 'Sty',
            february: 'Lut',
            march: 'Mar',
            april: 'Kwi',
            may: 'Maj',
            june: 'Cze',
            july: 'Lip',
            august: 'Sie',
            september: 'Wrz',
            october: 'Paź',
            november: 'Lis',
            december: 'Gru'
          },
          weeks: {
            monday: 'Pon',
            tuesday: 'Wt',
            wednesday: 'Śr',
            thursday: 'Czw',
            friday: 'Pt',
            saturday: 'Sob',
            sunday: 'Niedz'
          }
        },
        datepicker: {
          placeholder: 'wybierz datę',
          startTime: 'początek',
          endTime: 'koniec',
          customize: 'dostosuj',
          start: 'początek',
          end: 'koniec'
        },
        wordlimit: {
          warn: 'Możesz wpisać maksymalnie {0} słów'
        },
        wordcount: {
          warn: 'Przekroczyłeś limit {0} słów'
        },
        treepicker: {
          selectDesc: 'Wybrałeś {0} rzeczy',
          placeholder: 'proszę wybrać'
        },
        search: {
          placeholder: 'szukaj...',
          searchText: 'Szukaj'
        },
        taginput: {
          limitWords: 'Przekroczyłeś limit'
        },
        table: {
          empty: 'Brak danych'
        },
        uploader: {
          upload: 'Wgraj',
          reUpload: 'Wgraj ponownie'
        },
        pagination: {
          incorrectFormat: 'Niepoprawna wartość',
          overSize: 'Wartość przekracza zakres',
          totalBefore: 'Łącznie',
          totalAfter: 'pozycji',
          sizeOfPage: '{size} wyników/stronę'
        }
      }
    };
    var _default = lang;
    exports.default = _default;

    /***/ })

  /******/ })["default"]});;
