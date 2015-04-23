var now = new Date();
var format = function(time) {
	var date = new Date(time), diff = (((new Date(now)).getTime() - date
			.getTime()) / 1000), day_diff = Math.floor(diff / 86400);

	if (isNaN(day_diff) || day_diff < 0 || day_diff >= 31)
		return;

	return day_diff === 0
			&& (diff < 60 && "just now" || diff < 120 && "1 minute ago"
					|| diff < 3600 && Math.floor(diff / 60) + " minutes ago"
					|| diff < 7200 && "1 hour ago" || diff < 86400
					&& Math.floor(diff / 3600) + " hours ago")
			|| day_diff === 1 && "Yesterday" || day_diff < 7 && day_diff
			+ " days ago" || day_diff < 31 && Math.ceil(day_diff / 7)
			+ " weeks ago";
}

var loadRecentViewed = function(){
	$.getJSON("/api/v1/recentlyviewed", function(data) {
		var text="";
		$.each(data, function(index, value) {
			text+='<li class="list-group-item" style="padding-left: 30px;padding-right: 40px"><i class="glyphicon glyphicon-link"></i><a style="padding:5px" href=\"/api/v1/expand/'+value.SHORTURL +'\" target="_blank">'+
			 value.SHORTURL+'</a><span class="pull-right text-muted small"><em>'+format(value.MODIFIEDDATE)+'</em></span></li>';
		});
		$("#recent").html(text);
	});
}

var loadRecentlyShorted = function(){
	$.getJSON("/api/v1/recentlyshorten", function(data) {
		var txt="";
		$.each(data, function(index, value) {
			txt+='<li class="list-group-item" style="padding-left: 30px;padding-right: 40px" >'+ ' <i class="glyphicon glyphicon-link">'+'</i>'+'<a style="padding-left: 5px";'+' href='+'"'+'/api/v1/expand/'+value.SHORTURL +'"'+'target="_blank">'+
			 value.SHORTURL+'</a>'+
			' <span class="pull-right text-muted small">'+
			'<em>'+format(value.CREATEDDATE)+
			'</em>'+' </span>'+'</li>';
		});
		$("#trending").append(txt);
	});
}

var loadTable = function(){
	$.getJSON("/api/v1/listurl", function(data) {
			$("#table_id").dataTable( {
				"ordering": false,
				"bSort": false,
				"bFilter": false,
				"bLengthChange": false,
			    "data": data,
			    "columns": [
			        { "data": "SHORTURL"},
			        { "data": "COUNT"}
			    ]
			} );
	});
}

var loadChart = function(){
    $.getJSON("/api/v1/last5dayscount", function(data, status){
            var result = [], dt, year, month, day;
            $.each(data, function(index, value) {
            	var date = value.DATE;
            	dt1 = new Date(date);
            	dt = dt1.getDate();
            	month = dt1.getMonth();
            	year = dt1.getFullYear();
            	result.push([gd(year, month, dt), value.COUNT]);
            });
            plot(result);
        });
	
    function gd(year, month, day) {
    	var IST = ((5 * 60 * 60) + (30 * 60)) * 1000;
        return new Date(year, month-1 , day).getTime() + IST;
    }

    function plot(data) {
        var options = {
            series: {
                lines: {
                    show: true
                },
                points: {
                    show: true
                }
            },
            grid: {
                hoverable: true //IMPORTANT! this is needed for tooltip to work
            },
            xaxis: {
                    mode: "time",
                    minTickSize: [1, "day"],
                    timeformat: "%m/%d"
                },
            tooltip: true,
            tooltipOpts: {
                content: "'%s' on %x is %y",
                shifts: {
                    x: -60,
                    y: 25
                }
            }
        };

        var plotObj = $.plot($("#flot-line-chart"), [{
                data: data,
                label: "Url Short"
            }],
            options);
    }
}

$(document).ready(function() {
	loadRecentViewed();
	loadRecentlyShorted();
	loadTable();
	loadChart();
});