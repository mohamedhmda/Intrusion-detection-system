$(window).bind("load", function(){
    $.ajax({
        type : 'GET',
        url : 'NewServlet',
    });
});
var ctx = document.getElementById('myChart').getContext('2d');
var data_size = "";
var Predictions = [0 , 0];
var pred_chart2 = [
    {type:"normal", count:0},
    {type:"ddos", count:0},
    {type:"prob", count:0},
    {type:"u2r", count:0},
    {type:"r2l", count:0}    
];

var chart = new Chart(ctx, {
    type: 'doughnut' ,
    data: {
        labels: ["Attack", "Normal"],
        datasets: [{
                data: [Predictions[0],  Predictions[1]],
                backgroundColor: ["#F7464A", "#46BFBD"],
                hoverBackgroundColor: ["#FF5A5E", "#5AD3D1"]
            }]
    },
    options: {
        responsive: true
    }
});
var ctx2 = document.getElementById('myChart2').getContext('2d');
var chart2 = new Chart(ctx2, {
    type: 'bar',
    data: {
        labels: ['normal', 'ddos', 'prob', 'u2r','r2l'],
        datasets: [{
          label: 'attaques',
            backgroundColor: ["#46BFBD", "#F7464A","#4684BD","#000"],
            data: [pred_chart2[0].count, pred_chart2[1].count,pred_chart2[2].count, pred_chart2[3].count, pred_chart2[4].count]
        }]

    },
});
function updateChart(chart, x1){
	chart.data.datasets[0].data = [x1[0],x1[1]];
	chart.update();
};
function updateChart2(chart, y1){
	chart.data.datasets[0].data = [y1[0].count,y1[1].count,y1[2].count,y1[3].count,y1[4].count];
	chart.update();
};

var FilterNum;
function getNumber(val){
    switch(val){
        case 1:
            FilterNum = 0;
            break;
        case 2:
            FilterNum = 1;
            break;
        default:
            FilterNum = 0;
            break;
    }
};
function FilterFun(){
    // Declare variables
  var input, filter, table, tr, td, i, txtValue;
  input = document.getElementById("myInput");
  filter = input.value.toUpperCase();
  table = document.getElementById("myTable");
  tr = table.getElementsByTagName("tr");
  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[FilterNum];
    if (td) {
      txtValue = td.textContent || td.innerText;
      if (txtValue.toUpperCase().indexOf(filter) > -1) {
        tr[i].style.display = "";
      } else {
        tr[i].style.display = "none";
      }
    }
  }
};

function myFunction2(event){
  var div1 = document.getElementById("Info");
  var td = event.target.parentNode.cells;
  //event.target.outerHTML;
  //var t = td[0].textContent.concat(td[1].textContent);
  div1.innerHTML = "<td>" + td[0].textContent + "</td>"+"<td>" + td[1].textContent + "</td>";
  //div1.innerHTML = td[0].textContent + "," + td[1].textContent;
};


function doAjax(){
    console.log("i am in");
    $.ajax({
        type :'POST',
        url : 'NewServlet',
        success: function( result ) {
            var temps = data_size.split("/");
            $.each(result, function(index, item) {
                if(item != temps[index]){
                    $("<tr>",this).appendTo($("#table tbody"))
                        .append($("<td>").text(item.id))
                        .append($("<td>").text(item.time))
                        .append($("<td>").text(item.Source_ip))
                        .append($("<td>").text(item.Source_port))
                        .append($("<td>").text(item.Destination_ip))
                        .append($("<td>").text(item.Destination_port))
                        .append($("<td>").text(item.prediction));
                
                    if(item.prediction == "normal"){
                        Predictions[1]++;
                        pred_chart2[0].count++;
                    }else{
                        Predictions[0]++;
                    }
                    if(item.prediction == "ddos"){
                        pred_chart2[1].count++;
                    }else if(item.prediction == "prob"){
                        pred_chart2[2].count++;
                    }else if(item.prediction == "u2r"){
                        pred_chart2[3].count++;
                    }else if(item.prediction == "r2l"){
                        pred_chart2[4].count++;
                    }
                    updateChart(chart,Predictions);
                    updateChart2(chart2,pred_chart2);
                    data_size = data_size+item+"/";
                }
            });
        }
    })
};

var loading = setInterval(function(){
    doAjax() // this will run after every 5 seconds
}, 1000);
