// author: 王子涵
// 用于展示不同点距离，可以进行合并、缩放的雷达图
const CIR_PADDING = 150;
const COLOR_NORM = '#29a0ff';
const COLOR_EMERG = '#ff180d';
window.load_radar = () => {
  var typeLast = [];
  window.groups = [];
  window.items_vm = new Vue({
    el: '#radar-items',
    data: {
      items: [],
      maxD: 600,
      minD: 0,
      nowSelect: -1
    },
    methods: {
      click: function (id) {
        console.log(id);
        this.nowSelect = id;
      },
      // 顺序添加item
      add: function (data) {
        let o = {};
        o.dis = data.dist;
        o.type = data.type;
        o.id = data.id;
        o.name = data.name;
        // o.name = `${data.id}`;
        // o.color = data.emgcy ? COLOR_EMERG : COLOR_NORM;
        o.emerg = data.emgcy;
        o.pos = this.items.length;
        o.grouped = false;
        o.display = true;
        o.multiType = false;
        o.group = null;
        o.possible = [];
        o.trueName = data.name;
        o.trueEmerg = data.emgcy;
        o.trueDis = data.dist;
    
        if (this.insert(o)) {
          typeLast[o.type] = o;
          // console.log("type", o.type, "->", o.id);
        }
        this.items.push(o);
      },
      insert: function (obj) {
        var angles = [];
        for (let i = this.items.length - 1; i >= 0; i--) {
          let item = this.items[i];
          if (obj.dis - item.dis >= 50) break;
          if (!item.grouped)
            angles.push({id: i, angle: item.angle});
        }
        console.log(angles);
        if (!angles.length) {
          obj.angle = Math.floor(Math.random() * 360);
          return true;
        }
        angles.sort((a, b) => a.angle - b.angle);
        angles.push({id: angles[0].id, angles: angles[0].angle + 360});
        
        // 基于插入位置的碰撞判定
        var limit = 60 / Math.round(obj.dis / 100);
        console.log(limit);
        for (var i = 1; i < angles.length; i++)
          if (angles[i].angle - angles[i - 1].angle >= limit) {
            console.log("aaa")
            obj.angle = (angles[i - 1].angle + limit / 2 + Math.floor(Math.random() * (angles[i].angle - angles[i - 1].angle - limit))) % 360;
            obj.possible = [this.items[angles[i - 1].id], this.items[angles[i].id]];
            this.items[angles[i - 1]].possible.push(obj);
            this.items[angles[i]].possible.push(obj);
            return true;
          }
        // 消极的合并
        // console.log("merge", obj.id);
        if (typeLast[obj.type])
          (typeLast[obj.type].group || group(typeLast[obj.type])).add(obj);
        else
          for (let i = 0; ; i++)
            if (typeLast[i]) {
              (typeLast[i].group || group(typeLast[i])).add(obj);
              break;
            }
        return false;    
      },
      zoomOut: function () {
        this.maxD += 100;
        this.minD += 100;
        for (let i = 1; this.items[i].dis <= this.maxD; i++)
          if (!this.items[i].grouped) {
            let item = this.items[i];
            let dis = 20 + Math.max(24, item.dis - this.minD) * 1.5;
            if (dis <= 125)
              this.collapse(i);
            else
              item.possible.forEach(opp => {
                var oppdis = 20 + Math.max(24, opp.dis - this.minD) * 1.5;
                if (collise(dis, item.angle, oppdis, opp.angle)) {
                  this.collapse(i);
                  return;
                }
              });
          }
      },
      zoomIn: function () {
        this.minD -= 100;
        this.maxD -= 100;
        groups.forEach((group, i) => {
          if (group.release())
            groups.splice(i, 1);
        });
      },
      collapse: function (i) {
        let item = this.items[i];
        for (var j = i - 1; j >= 0 && this.items[j].dis + 70 > item.dis; j--) {
          if (!this.items[j].grouped && this.items[j].type == item.type) {
            if (this.items[j].group)
              this.items[j].group.add(item);
            else
              group(this.items[j]).add(item);
            return;
          }
        }
        for (var j = i - 1; j >= 0; j--)
          if (!this.items[j].grouped) {
            if (this.items[j].group)
              this.items[j].group.add(item);
            else
              group(this.items[j]).add(item);
            return;
          }
      },
      clear: function () {
        this.minD = 0; this.maxD = 600;
        this.items = [];
        typeLast = [];
        groups = [];
      }
    }
  });

  // circle and zooming
  var circles = [];
  for (let i = 0; i < 6; i++) {
    let circle = document.createElement('div');
    circle.className = 'radar-circle';
    circles.push(circle);
    circle.style = `--level: ${i}`;
    $('#radar-circles').append(circle);
  }
  var zooming = false;
  $('#radar-items')[0].addEventListener('wheel', function (e) {
    if (zooming || items_vm.nowSelect != -1) return;
    if (e.deltaY > 0)
      zoomIn();
    else
      zoomOut();
  });
  document.body.addEventListener('keydown', function (e) {
    if (zooming) return;
    if (e.key == 'ArrowDown') zoomOut();
    if (e.key == 'ArrowUp') zoomIn();
  });
  circles[2].addEventListener('transitionend', function () {
    zooming = false;
    for (let i = 0; i < 6; i++) {
      circles[i].style = `--level: ${i}`;
      circles[i].className = 'radar-circle';
    }
  });
  $('#radar-circles')[0].addEventListener('animationend', function () {
    this.className = '';
  });
  function zoomIn() {
    if (items_vm.minD == 0) {
      $('#radar-circles').addClass('ceil');
      return;
    }
    zooming = true;
    for (let i = 0; i < 5; i++) {
      circles[i].style = `--level: ${i + 1}`;
      circles[i].className = 'radar-circle move';
    }
    items_vm.zoomIn();
  }
  function zoomOut() {
    if (items_vm.minD == 2000) {
      $('#radar-circles').addClass('floor');
      return;
    }
    zooming = true;
    for (let i = 1; i < 6; i++) {
      circles[i].style = `--level: ${i - 1}`;
      circles[i].className = 'radar-circle move';
    }
    items_vm.zoomOut();
  }

  // utility
  function group(obj) {
    var o = {};
    var count = 1;
    var groupObj = obj;
    var list = [];
    // console.log("set group", groups.length, "->", obj.id);
    Object.defineProperties(o, {
      'add': {
        value: function (obj) {
          // console.log("add", obj.id, "->", groupObj.id);
          list.push({obj: obj, max: items_vm.minD, dis: obj.dis, angle: obj.angle});
          if (obj.type != groupObj.type) groupObj.multiType = true;
          obj.dis = groupObj.dis;
          obj.angle = groupObj.angle;
          obj.grouped = true;
          setTimeout(() => {
            obj.display = false;
          }, 300);
          if (obj.group)
            count += obj.group.count;
          else
            count++;
          groupObj.name = `${count}个`;
          if (obj.emerg)
            groupObj.emerg = true;
        }
      },
      'release': {
        value: function () {
          if (groupObj.grouped) return;
          var typeMulti = false, hasEmerg = false;
          list = list.filter(item => {
            if (items_vm.minD < item.max) {
              item.obj.grouped = false;
              item.obj.display = true;
              setTimeout(() => {
                item.obj.dis = item.dis;
                item.obj.angle = item.angle;
              }, 0);
              if (item.obj.group)
                count -= item.obj.group.count;
              else
                count--;
              return false;
            }
            if (item.type != groupObj.type)
              typeMulti = true;
            if (item.emerg) hasEmerg = true;
            return true;
          });
          if (!typeMulti) groupObj.multiType = false;
          if (!hasEmerg) groupObj.emerg = groupObj.trueEmerg;
          if (list.length == 0) {
            groupObj.name = groupObj.trueName;
            groupObj.group = null;
            return true;
          }
          else {
            groupObj.name = `${count}个`;
            return false;
          }
        }
      },
      'count': {
        get: function () {
          return count;
        }
      },
      'list': {
        get: function () {
          var full = [groupObj];
          list.forEach(obj => obj.obj.group ? full.push(... obj.obj.group.list) : full.push(obj.obj));
          return full;
        }
      }
    });
    groups.push(groupObj.group = o);
    return o;
  }
  
  const PI180 = Math.PI / 180;
  function collise(disA, angA, disB, angB) {
    angA *= PI180; angB *= PI180;
    var dx = disA * Math.cos(angA) - disB * Math.cos(angB);
    var dy = disA * Math.sin(angA) - disB * Math.sin(angB);
    console.log("get")
    return dx * dx + dy * dy < 4900;
  }
  
  document.body.addEventListener('click', () => {
    items_vm.nowSelect = -1;
  }, true);
};