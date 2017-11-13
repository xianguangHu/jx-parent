<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../base.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>amCharts examples</title>
        <link rel="stylesheet" href="${ctx}/js/amcharts/style.css" type="text/css">
        <script src="${ctx}/js/amcharts/amcharts/amcharts.js" type="text/javascript"></script>
        <script src="${ctx}/js/amcharts/amcharts/serial.js" type="text/javascript"></script>

        <script>
            var chart;

//            var chartData = [
//                {
//                    "country": "Czech Republic",
//                    "litres": 156.9,
//                    "short": "CZ"
//                },
//                {
//                    "country": "Ireland",
//                    "litres": 131.1,
//                    "short": "IR"
//                },
//                {
//                    "country": "Germany",
//                    "litres": 115.8,
//                    "short": "DE"
//                },
//                {
//                    "country": "Australia",
//                    "litres": 109.9,
//                    "short": "AU"
//                },
//                {
//                    "country": "Austria",
//                    "litres": 108.3,
//                    "short": "AT"
//                },
//                {
//                    "country": "UK",
//                    "litres": 99,
//                    "short": "UK"
//                },
//                {
//                    "country": "Belgium",
//                    "litres": 93,
//                    "short": "BE"
//                }
//            ];

            AmCharts.ready(function () {
                // SERIAL CHART
                var chart = new AmCharts.AmSerialChart();
                chart.dataProvider = ${chartData};
                chart.categoryField = "产品";
                chart.startDuration = 2;
                // change balloon text color
                chart.balloon.color = "#000000";

                // AXES
                // category
                var categoryAxis = chart.categoryAxis;
                categoryAxis.gridAlpha = 0;
                categoryAxis.axisAlpha = 0;
                categoryAxis.labelsEnabled = false;

                // value
                var valueAxis = new AmCharts.ValueAxis();
                valueAxis.gridAlpha = 0;
                valueAxis.axisAlpha = 0;
                valueAxis.labelsEnabled = false;
                valueAxis.minimum = 0;
                chart.addValueAxis(valueAxis);

                // GRAPH
                var graph = new AmCharts.AmGraph();
                graph.balloonText = "[[category]]: [[value]]";
                graph.valueField = "销量";
                graph.descriptionField = "名称";
                graph.type = "column";
                graph.lineAlpha = 0;
                graph.fillAlphas = 1;
                graph.fillColors = ["#ffe78e", "#bf1c25"];
                graph.labelText = "[[description]]";
                graph.balloonText = "[[category]]: [[value]] 产品";
                chart.addGraph(graph);

                chart.creditsPosition = "top-right";

                // WRITE
                chart.write("chartdiv");
            });
        </script>
    </head>

    <body>
        <div id="chartdiv" style="width: 520px; height: 400px;"></div>
    </body>

</html>