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
  var userid;
  if (userid = document.cookie.match(/userid=[0-9]*/)) {
    userid = userid[0];
    $('#sign-in').css('display', 'none');
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
    $('#add-request-btn').click(function () {

    });
    $('#order-list-btn').click(function () {

    });
    $('#user-profile-btn').click(function () {

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
          $.post('/user/login', {user_name: this.username, password: md5(this.passwd + 'hackathon')}, function (data) {
            if (data == 'false')
              alert('用户名或密码错误！');
            else {
              var d = new Date();
              d.setTime(d.getTime() + (7*24*60*60*1000));
              document.cookie = `userid=${data.slice(2, 5)};expires=${d.toUTCString()};path=/`;
              location.reload();
            }
          });
        },
        signUp: function () {
          location.href = 'signUp.html';
        }
      }
    });
  }
});