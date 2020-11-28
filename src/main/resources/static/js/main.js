var radar_data = [];
// fake data
for (let i = 0; i < 100; i++) {
  let type = Math.floor(Math.random() * 4);
  let distance = Math.random() * 2000 + 30;
  radar_data.push({type: type, dist: distance, emgcy: false, id: i, name: '测试测试测试'});
}
for (let i = 0; i < 3; i++) {
  let type = Math.floor(Math.random() * 4);
  let distance = Math.random() * 2000 + 30;
  radar_data.push({type: type, dist: distance, emgcy: true, id: 100 + i, name: '测试'});
}
radar_data.sort((a, b) => a.dist - b.dist);

window.addEventListener("load", function () {
  var graphNow = 0;
  $('#sell-graph-btn').click(function () {
    if (graphNow == 0) return;
    $('#sell-graph-btn').addClass('selected');
    $('#buy-graph-btn').removeClass('selected');
    graphNow = 0;
  });
  $('#buy-graph-btn').click(function () {
    if (graphNow == 1) return;
    $('#buy-graph-btn').addClass('selected');
    $('#sell-graph-btn').removeClass('selected');
    graphNow = 1;
  });
});