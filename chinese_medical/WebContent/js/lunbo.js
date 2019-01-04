    window.onload = function () {
      var banner1 = new Banner("box");
      banner1.fnInital();

      var banner2 = new ChildBanner("box2");
      banner2.fnInital();
    };

    function Banner(id) {
      this.oBox = document.getElementById(id);
      this.img_wrap = fnGetByCls(this.oBox, "div", "img_wrap")[0];
      this.imgMenu = this.img_wrap.getElementsByTagName("a");
      this.btn_wrap = fnGetByCls(this.oBox, "div", "btn_wrap")[0];
      this.btnMenu = this.btn_wrap.getElementsByTagName("a");

      this.img_w = this.imgMenu[0].offsetWidth;
      this.img_h = this.imgMenu[0].offsetHeight;
      this.iNow = 0;
      this.iNow2 = 0;
      this.timer = null;
      this.b_stop = true;
    };

    Banner.prototype.fnInital = function () {
      var _that = this; // 当前对象的 this

      for (var i = 0; i < this.btnMenu.length; i++) {
        this.btnMenu[i].index = i;

        this.btnMenu[i].onclick = function () {
          if (_that.b_stop) {
            _that.b_stop = false;

            _that.fnTab(this.index);

            clearInterval(_that.timer);

            $(_that.img_wrap).animate({left: -this.index * _that.img_w}, 800, function () {
              _that.timer = setInterval(function () {
                _that.fnPlay();
              }, 3000);

              _that.b_stop = true;
            });

            _that.iNow = this.index;
            _that.iNow2 = this.index;
          }
        };
      }

      this.fnSetWidth(); // 设置宽度
      this.timer = setInterval(function () {
        _that.fnPlay();
      }, 3000); // 自动播放
    };

    Banner.prototype.fnSetWidth = function () {
      this.img_wrap.style.width = this.img_w * this.imgMenu.length + "px";
    };

    Banner.prototype.fnTab = function (index) { // 这里接受到的是一个下标
      for (var j = 0; j < this.btnMenu.length; j++) {
        this.btnMenu[j].setAttribute("class", "");
      }

      this.btnMenu[index].setAttribute("class", "btn_cur");
    };

    Banner.prototype.fnPlay = function () {
      var _that = this;

      if (this.b_stop) {
        this.b_stop = false;

        if (this.iNow == this.imgMenu.length - 1) {
          this.iNow = 0;

          this.imgMenu[0].style.position = "relative";
          this.imgMenu[0].style.left = this.img_w * this.imgMenu.length + "px";
        } else {
          this.iNow ++;
        }

        this.iNow2 ++;

        this.fnTab(this.iNow);

        $(this.img_wrap).animate({left: -this.iNow2 * this.img_w}, 800, function () {
          if (_that.iNow == 0) {
            _that.iNow2 = 0;

            _that.imgMenu[0].style.position = "static";
            _that.img_wrap.style.left = 0;
          }

          _that.b_stop = true;
        });
      }
    };


    // 继承下来的子轮播图
    function ChildBanner(id) {
      Banner.call(this, id);
    };

    fnExtend(Banner.prototype, ChildBanner.prototype);

    ChildBanner.prototype.fnInital = function () {
      var _that = this; // 当前对象的 this

      for (var i = 0; i < this.btnMenu.length; i++) {
        this.btnMenu[i].index = i;

        this.btnMenu[i].onclick = function () {
          if (_that.b_stop) {
            _that.b_stop = false;

            _that.fnTab(this.index);

            clearInterval(_that.timer);

            $(_that.img_wrap).animate({top: -this.index * _that.img_h}, 800, function () {
              _that.timer = setInterval(function () {
                _that.fnPlay();
              }, 3000);

              _that.b_stop = true;
            });

            _that.iNow = this.index;
            _that.iNow2 = this.index;
          }
        };
      }

      this.fnSetWidth(); // 设置宽度
      this.timer = setInterval(function () {
        _that.fnPlay();
      }, 3000); // 自动播放
    };

    // 这里要重写上面的 fnSetWidth
    ChildBanner.prototype.fnSetWidth = function () {
      this.img_wrap.style.height = this.img_h * this.imgMenu.length + "px";
    };

    ChildBanner.prototype.fnPlay = function () {
      var _that = this;

      if (this.b_stop) {
        this.b_stop = false;

        if (this.iNow == this.imgMenu.length - 1) {
          this.iNow = 0;

          this.imgMenu[0].style.position = "relative";
          this.imgMenu[0].style.top = this.img_h * this.imgMenu.length + "px";
        } else {
          this.iNow ++;
        }

        this.iNow2 ++;

        this.fnTab(this.iNow);

        $(this.img_wrap).animate({top: -this.iNow2 * this.img_h}, 800, function () {
          if (_that.iNow == 0) {
            _that.iNow2 = 0;

            _that.imgMenu[0].style.position = "static";
            _that.img_wrap.style.top = 0;
          }

          _that.b_stop = true;
        });
      }
    };



    // 下面是需要用到的函数

    function fnGetByCls(oParent, tagName, sCls) {
      var aElem = oParent.getElementsByTagName(tagName);
      var result = [];

      for (var i = 0; i < aElem.length; i++) {
        var get_cls = aElem[i].className.split(" ");

        for (var j = 0; j < get_cls.length; j++) {
          if (get_cls[j] == sCls) {
            result.push(aElem[i]);
          }

          break;
        }
      }

      return result;
    };

    function fnExtend(parentObj, childObj) {
      for (var attr in parentObj) {
        childObj[attr] = parentObj[attr];
      }
    };