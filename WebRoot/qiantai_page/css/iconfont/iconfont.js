;(function(window) {

  var svgSprite = '<svg>' +
    '' +
    '<symbol id="icon-ziyuan" viewBox="0 0 1024 1024">' +
    '' +
    '<path d="M512 0a512 512 0 1 0 512 512A512 512 0 0 0 512 0z m0 972.8a460.8 460.8 0 1 1 460.8-460.8 460.8 460.8 0 0 1-460.8 460.8z"  ></path>' +
    '' +
    '</symbol>' +
    '' +
    '<symbol id="icon-wancheng" viewBox="0 0 1024 1024">' +
    '' +
    '<path d="M512 0a512 512 0 1 0 512 512A512 512 0 0 0 512 0z m298.5472 357.1712l-370.944 361.0112a35.84 35.84 0 0 1-50.688-0.6656l-0.512-0.4608-175.6672-180.5312a35.84 35.84 0 0 1 0.6656-50.688 35.84 35.84 0 0 1 50.688 0.6656l151.1424 155.3408 345.2928-336.0256a35.84 35.84 0 0 1 49.9712 51.2z"  ></path>' +
    '' +
    '</symbol>' +
    '' +
    '<symbol id="icon-xuexizhong" viewBox="0 0 1024 1024">' +
    '' +
    '<path d="M512 0a512 512 0 1 0 512 512A512 512 0 0 0 512 0z m0 51.2a460.8 460.8 0 0 1 460.8 460.8H51.2a460.8 460.8 0 0 1 460.8-460.8z"  ></path>' +
    '' +
    '</symbol>' +
    '' +
    '</svg>'
  var script = function() {
    var scripts = document.getElementsByTagName('script')
    return scripts[scripts.length - 1]
  }()
  var shouldInjectCss = script.getAttribute("data-injectcss")

  /**
   * document ready
   */
  var ready = function(fn) {
    if (document.addEventListener) {
      if (~["complete", "loaded", "interactive"].indexOf(document.readyState)) {
        setTimeout(fn, 0)
      } else {
        var loadFn = function() {
          document.removeEventListener("DOMContentLoaded", loadFn, false)
          fn()
        }
        document.addEventListener("DOMContentLoaded", loadFn, false)
      }
    } else if (document.attachEvent) {
      IEContentLoaded(window, fn)
    }

    function IEContentLoaded(w, fn) {
      var d = w.document,
        done = false,
        // only fire once
        init = function() {
          if (!done) {
            done = true
            fn()
          }
        }
        // polling for no errors
      var polling = function() {
        try {
          // throws errors until after ondocumentready
          d.documentElement.doScroll('left')
        } catch (e) {
          setTimeout(polling, 50)
          return
        }
        // no errors, fire

        init()
      };

      polling()
        // trying to always fire before onload
      d.onreadystatechange = function() {
        if (d.readyState == 'complete') {
          d.onreadystatechange = null
          init()
        }
      }
    }
  }

  /**
   * Insert el before target
   *
   * @param {Element} el
   * @param {Element} target
   */

  var before = function(el, target) {
    target.parentNode.insertBefore(el, target)
  }

  /**
   * Prepend el to target
   *
   * @param {Element} el
   * @param {Element} target
   */

  var prepend = function(el, target) {
    if (target.firstChild) {
      before(el, target.firstChild)
    } else {
      target.appendChild(el)
    }
  }

  function appendSvg() {
    var div, svg

    div = document.createElement('div')
    div.innerHTML = svgSprite
    svgSprite = null
    svg = div.getElementsByTagName('svg')[0]
    if (svg) {
      svg.setAttribute('aria-hidden', 'true')
      svg.style.position = 'absolute'
      svg.style.width = 0
      svg.style.height = 0
      svg.style.overflow = 'hidden'
      prepend(svg, document.body)
    }
  }

  if (shouldInjectCss && !window.__iconfont__svg__cssinject__) {
    window.__iconfont__svg__cssinject__ = true
    try {
      document.write("<style>.svgfont {display: inline-block;width: 1em;height: 1em;fill: currentColor;vertical-align: -0.1em;font-size:16px;}</style>");
    } catch (e) {
      console && console.log(e)
    }
  }

  ready(appendSvg)


})(window)