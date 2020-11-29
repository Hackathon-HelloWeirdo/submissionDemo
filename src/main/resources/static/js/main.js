var request_data = [];
// fake data
for (let i = 0; i < 100; i++) {
  let type = Math.floor(Math.random() * 4);
  let distance = Math.random() * 2000 + 60;
  request_data.push({ type: type, dist: distance, emgcy: false, id: i, name: '测试测试测试' });
}
for (let i = 0; i < 3; i++) {
  let type = Math.floor(Math.random() * 4);
  let distance = Math.random() * 2000 + 60;
  request_data.push({ type: type, dist: distance, emgcy: true, id: 100 + i, name: '测试' });
}
request_data.sort((a, b) => a.dist - b.dist);
var supply_data = [];
// fake data
for (let i = 0; i < 100; i++) {
  let type = Math.floor(Math.random() * 4);
  let distance = Math.random() * 2000 + 60;
  supply_data.push({ type: type, dist: distance, emgcy: false, id: i, name: '测试测试测试' });
}
for (let i = 0; i < 3; i++) {
  let type = Math.floor(Math.random() * 4);
  let distance = Math.random() * 2000 + 60;
  supply_data.push({ type: type, dist: distance, emgcy: true, id: 100 + i, name: '测试' });
}
supply_data.sort((a, b) => a.dist - b.dist);

window.addEventListener("load", function () {
  var graphNow = 0;
  var userid, lastAddr = '';
  if (userid = document.cookie.match(/userid=[0-9]*/)) {
    userid = userid[0];
    $('#sign-in').css('display', 'none');
    /*$.get('/reqGoods/getAll', {}, function (data) {
      console.log(data);
      //process
      $.get('/supGoods/getAll', {}, function (data) {
        console.log(data);
        //process*/
        load_radar();
        $('#radar').css('display', 'block');
        supply_data.forEach(data => items_vm.add(data));
        $('#sell-graph-btn').click(function () {
          if (graphNow == 0) return;
          $('#sell-graph-btn').addClass('selected');
          $('#buy-graph-btn').removeClass('selected');
          graphNow = 0;
          items_vm.clear();
          supply_data.forEach(data => items_vm.add(data));
        });
        $('#buy-graph-btn').click(function () {
          if (graphNow == 1) return;
          $('#buy-graph-btn').addClass('selected');
          $('#sell-graph-btn').removeClass('selected');
          graphNow = 1;
          items_vm.clear();
          request_data.forEach(data => items_vm.add(data));
        });
      /*});
    });*/
    $('#add-reqsup-btn').click(function () {
      if (graphNow == 0) {
        sup_vm.clear();
        $('#add-sup').css('display', 'block');
      }
      else {
        req_vm.clear();
        $('#add-req').css('display', 'block');
      }
    });
    $('#order-list-btn').click(function () {
    });
    $('#user-profile-btn').click(function () {
      // $('#user-profile').css('display', 'block');
    });
    var req_vm = new Vue({
      el: '#add-req',
      data: {
        name: '',
        type: 0,
        intro: '',
        address: lastAddr
      },
      computed: {
        valid: function () {
          return this.name.length && this.address.length;
        }
      },
      methods: {
        submit: function () {
          $.post('/reqGoods/saveReGoods', {id: userid, req_name: `${this.type}||${this.name}`, describe: this.intro, address: this.address});
          lastAddr = this.address;
          $('#add-req').css('display', 'none');
        },
        clear: function () {
          this.name = this.intro = '';
          this.address = lastAddr;
          this.type = 0;
        },
        close: function () {
          $('#add-req').css('display', 'none');
        }
      }
    });
    var sup_vm = new Vue({
      el: '#add-sup',
      data: {
        name: '',
        type: 0,
        intro: '',
        address: lastAddr,
        image: ''
      },
      computed: {
        valid: function () {
          return this.name.length && this.address.length;
        }
      },
      methods: {
        submit: function () {
          $.post('/supGoods/saveSupGoods', {id: userid, req_name: `${this.type}||${this.name}`, describe: this.intro, address: this.address, image: this.image});
          lastAddr = this.address;
          $('#add-sup').css('display', 'none');
        },
        clear: function () {
          this.name = this.intro = this.image = '';
          this.address = lastAddr;
          this.type = 0;
        },
        close: function () {
          $('#add-sup').css('display', 'none');
        }
      }
    });
  }
  else {
    var signIn_vm = new Vue({
      el: "#sign-in",
      data: {
        username: '',
        passwd: ''
      },
      computed: {
        valid: function () {
          return this.username.length && this.passwd.length;
        }
      },
      methods: {
        submit: function () {
          /*$.post('/user/login', {user_name: this.username, password: md5(this.passwd + 'hackathon')}, function (data) {
            if (data == 'false')
              alert('用户名或密码错误！');
            else {*/
              data = 'ok23';
              var d = new Date();
              d.setTime(d.getTime() + (7*24*60*60*1000));
              document.cookie = `userid=${data.slice(2, 5)};expires=${d.toUTCString()};path=/`;
              location.reload();
            /*}
          });*/
        },
        signUp: function () {
          location.href = 'signUp.html';
        }
      }
    });
  }
});