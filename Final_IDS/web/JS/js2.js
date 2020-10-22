const app = {
    data_size : "",
    data_store : [],
    Predictions : [0 , 0],
    pred_chart2 : [
        {type:"normal", count:0},
        {type:"ddos", count:0},
        {type:"prob", count:0},
        {type:"u2r", count:0},
        {type:"r2l", count:0}    
    ],
    chart : "",
    chart2 : "",
    FilterNum : 0,
    init: () => {
        document.addEventListener('DOMContentLoaded', app.load);
    },
    load: () => {        
        app.getData();
    },
    getData: () => {
        let page = document.body.id;
        switch(page) {
            case 'page1':                
                app.getP1();
                break;
            case 'page2':
                app.getP2();
                break;
            case 'page3':
                app.getP3();
                break;
            default :
                app.Somelse();
        }
    },
    getP1: () => {
        app.doAjaxP1();
        var ctx = document.getElementById('myChart').getContext('2d');
        app.chart = new Chart(ctx, {
            type: 'doughnut' ,
            data: {
                labels: ["Attack", "Normal"],
                datasets: [{
                        data: [app.Predictions[0],  app.Predictions[1]],
                        backgroundColor: ["#F7464A", "#46BFBD"],
                        hoverBackgroundColor: ["#FF5A5E", "#5AD3D1"]
                    }]
            },
            options: {
                responsive: true
            }
        });        
        var ctx2 = document.getElementById('myChart2').getContext('2d');
        app.chart2 = new Chart(ctx2, {
            type: 'bar',
            data: {
                labels: ['normal', 'ddos', 'prob', 'u2r','r2l'],
                datasets: [{
                  label: 'normal',
                    backgroundColor: ["#46BFBD", "#F7464A","#4684BD","#000"],
                    data: [app.pred_chart2[0].count, app.pred_chart2[1].count, app.pred_chart2[2].count, app.pred_chart2[3].count, app.pred_chart2[4].count]
                }]

            },
        });
        //setInterval(app.doAjaxP1,1500);
    },
    updateChart: (chart, x1) => {
        chart.data.datasets[0].data = [x1[0],x1[1]];
	chart.update();
    },
    updateChart2: (chart, y1) => {
        chart.data.datasets[0].data = [y1[0].count,y1[1].count,y1[2].count,y1[3].count,y1[4].count];
	chart.update();
    },
    getP2: () => {
        app.doAjaxP2();
        //setInterval(app.doAjaxP2,1500);
    },
    getNumber: (val) => {
        switch(val){
            case 1:
                app.FilterNum = 0;
                break;
            case 2:
                app.FilterNum = 1;
                break;
            default:
                app.FilterNum = 0;
                break;
        }
    },
    FilterFun: () => {
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
    },
    vecteur_list : [
        "logged_in : ",
        "dst_host_same_srv_rate : ",
        "dst_host_serror_rate : ",
        "same_srv_rate : ",
        "dst_host_srv_serror_rate : ",
        "dst_host_srv_count : ",
        "dst_host_srv_rerror_rate : ",
        "srv_rerror_rate : ",
        "dst_host_same_src_port_rate : ",
        "protocol_type : ",
        "flag : ",
        "service : ",
        "count : ",
        "diff_srv_rate : ",
        "is_guest_login : ",
        "root_shell : ",
        "wrong_fragment : "
    ],
    myFunction2: (event) => {
        var div1 = document.getElementById("Info");
        var td = event.target.parentNode.cells;
        var id = td[0].textContent;
        var tr = event.target.parentNode;
        if ( tr.classList.add("selected") ) {
            tr.classList.remove("selected");
        }
        else {
            //$('#table').$('tr.selected').removeClass('selected');
            tr.classList.add("selected") ;
        }
        $.ajax({
            type :'POST',
            url : 'NewServlet',
            data : { Page : "3", id : id},
            success: function( result ) {
                $('#Info li').each(function(i){
                   $(this).html(app.vecteur_list[i]+'<b>'+result[i]+'</b>'); 
                });
            }
        });
    },
    doAjaxP1: () => {
        $.ajax({
            type :'POST',
            url : 'NewServlet',
            data : { Page : "1"},
            success: function( result ) {
                //app.looping(result);
                app.Predictions[1] = result.Prediction[0];
                app.Predictions[0] = result.Prediction[1];
                
                app.pred_chart2[0].count = result.pred_chart2[0];
                app.pred_chart2[1].count = result.pred_chart2[1];
                app.pred_chart2[2].count = result.pred_chart2[2];
                app.pred_chart2[3].count = result.pred_chart2[3];
                app.pred_chart2[4].count = result.pred_chart2[4];
                
                var total = result.Prediction[0] + result.Prediction[1];
                $('#totalP').html('<b> Total : '+total+'</b>');
                
                var acc = ((result.VN+result.VP)/(total))*100;
                
                $('#time').html('execution time : '+result.time);
                $('#acc').html('Accuracy : '+acc+'%');
                $('#VN').html('True negative : '+result.VN);
                $('#FP').html('False positive : '+result.FP);
                $('#FN').html('False negative : '+result.FN);
                $('#VP').html('True positive : '+result.VP);
                
                
                
                app.updateChart(app.chart,app.Predictions);
                app.updateChart2(app.chart2,app.pred_chart2);
            }, 
            complete: function( result ){
                setTimeout(app.doAjaxP1,1000);
            }
        })
    },
    doAjaxP2: () => {        
        var table = $('#table').DataTable( {            
            "deferRender": true,
            "ajax": {
                "data" : {Page : "2"},
                "processing": true,
                "serverSide": true,
                "url" : "NewServlet",
                "type" : "post",
                "dataSrc": ""
            },
            "aoColumns": [
                        {"mData" : "id"},
                        {"mData" : "time"},
                        {"mData" : "Source_ip"},
                        {"mData" : "Source_port"},
                        {"mData" : "Destination_ip"},
                        {"mData" : "Destination_port"},
                        {"mData" : "prediction"},
                        {"mData" : "prediction_lvl2"}
                    ],
            "rowId": 'id',
            "select": true,
            "dom": 'Bfrtip'
        });
        setInterval( function () {
            table.ajax.reload( null, false ); // user paging is not reset on reload
        }, 2000 );
    },
    getP3: () => {
        
    },
    DatasetDisplay: (DataTitle) => {
        switch(DataTitle){
            case 1:
                app.Training();
                break;
            case 2:
                app.Coded();
                break;
            case 3:
                app.Normalised();
                break;
            default:
                app.Somelse();
        }
    },
    training_table : false,
    Training: () => {
        var x = document.getElementById("myDIV");
                var y = document.getElementById("myDIV2");
                var w = document.getElementById("myDIV3");
                var x2 = document.getElementById("Training_tab");
        var y2 = document.getElementById("Codage_tab");
        var w2 = document.getElementById("Norm_tab");
                if (x.style.display === "none") {
                    y.style.display = "none";
                    y2.classList.remove("active");
                    w.style.display = "none";
                    w2.classList.remove("active");
                    x.style.display = "block";
                    x2.classList.add("active");
                }
        if(!app.training_table){
            app.training_table = true;
            $('#trainingTable').DataTable( {
                "ajax": {
                    "data" : {BD : "1"},
                    "processing": true,
                    "serverSide": true,
                    "url" : "Info",
                    "type" : "post",
                    "dataSrc": ""
                },
                "aoColumns": [
                            {"mData" : "logged_in"},
                            {"mData" : "dst_host_same_srv_rate"},
                            {"mData" : "dst_host_serror_rate"},
                            {"mData" : "same_srv_rate"},
                            {"mData" : "dst_host_srv_serror_rate"},
                            {"mData" : "dst_host_srv_count"},
                            {"mData" : "dst_host_srv_rerror_rate"},
                            {"mData" : "srv_rerror_rate"},
                            {"mData" : "dst_host_same_src_port_rate"},
                            {"mData" : "protocol_type"},
                            {"mData" : "flag"},
                            {"mData" : "service"},
                            {"mData" : "count"},
                            {"mData" : "diff_srv_rate"},
                            {"mData" : "is_guest_login"},
                            {"mData" : "root_shell"},
                            {"mData" : "wrong_fragment"},
                            {"mData" : "attaque_name"}
                        ]
            });
        }
    },
    coded_table : false,
    Coded: () => {   
        var x = document.getElementById("myDIV");
        var y = document.getElementById("myDIV2");
        var w = document.getElementById("myDIV3");
        var x2 = document.getElementById("Training_tab");
        var y2 = document.getElementById("Codage_tab");
        var w2 = document.getElementById("Norm_tab");
        if (y.style.display === "none") {
            x.style.display = "none";
            x2.classList.remove("active");
            w.style.display = "none";
            w2.classList.remove("active");
            y.style.display = "block";
            y2.classList.add("active");
        }
        if(!app.coded_table){
            app.coded_table = true;
            $('#CodedTable').DataTable( {
                "ajax": {
                    "data" : {BD : "2"},
                    "processing": true,
                    "serverSide": true,
                    "url" : "Info",
                    "type" : "post",
                    "dataSrc": ""
                },
                "aoColumns": [
                            {"mData" : "logged_in"},
                            {"mData" : "dst_host_same_srv_rate"},
                            {"mData" : "dst_host_serror_rate"},
                            {"mData" : "same_srv_rate"},
                            {"mData" : "dst_host_srv_serror_rate"},
                            {"mData" : "dst_host_srv_count"},
                            {"mData" : "dst_host_srv_rerror_rate"},
                            {"mData" : "srv_rerror_rate"},
                            {"mData" : "dst_host_same_src_port_rate"},
                            {"mData" : "protocol_type"},
                            {"mData" : "flag"},
                            {"mData" : "service"},
                            {"mData" : "count"},
                            {"mData" : "diff_srv_rate"},
                            {"mData" : "is_guest_login"},
                            {"mData" : "root_shell"},
                            {"mData" : "wrong_fragment"},
                            {"mData" : "attaque_name"}
                        ]
            });
        }
    },
    norm_table : false,
    Normalised: () => {
        var x = document.getElementById("myDIV");
        var y = document.getElementById("myDIV2");
        var w = document.getElementById("myDIV3");
        var x2 = document.getElementById("Training_tab");
        var y2 = document.getElementById("Codage_tab");
        var w2 = document.getElementById("Norm_tab");
        if (w.style.display === "none") {
            x.style.display = "none";
            x2.classList.remove("active");
            y.style.display = "none";
            y2.classList.remove("active");
            w.style.display = "block";
            w2.classList.add("active");
        }
        if(!app.norm_table){
            app.norm_table = true;
            $('#NormTable').DataTable( {
                "ajax": {
                    "data" : {BD : "3"},
                    "processing": true,
                    "serverSide": true,
                    "url" : "Info",
                    "type" : "post",
                    "dataSrc": ""
                },
                "aoColumns": [
                            {"mData" : "logged_in"},
                            {"mData" : "dst_host_same_srv_rate"},
                            {"mData" : "dst_host_serror_rate"},
                            {"mData" : "same_srv_rate"},
                            {"mData" : "dst_host_srv_serror_rate"},
                            {"mData" : "dst_host_srv_count"},
                            {"mData" : "dst_host_srv_rerror_rate"},
                            {"mData" : "srv_rerror_rate"},
                            {"mData" : "dst_host_same_src_port_rate"},
                            {"mData" : "protocol_type"},
                            {"mData" : "flag"},
                            {"mData" : "service"},
                            {"mData" : "count"},
                            {"mData" : "diff_srv_rate"},
                            {"mData" : "is_guest_login"},
                            {"mData" : "root_shell"},
                            {"mData" : "wrong_fragment"},
                            {"mData" : "attaque_name"}
                        ]
            });
        }
    },
    Somelse: () => {
        //nothing
    },
}
app.init();