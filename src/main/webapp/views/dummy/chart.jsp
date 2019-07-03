<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <%@include file="../include/header.jsp"%>

    <script type="text/javascript">
        var seed = Date.now();

        var lineChartData = {
            labels: ['00:00', '00:10', '00:20', '00:30', '00:40', '00:50', '00:60'],
            datasets: [{
                label: 'user',
                borderColor: "rgb(255, 99, 132)",
                backgroundColor: "rgb(255, 99, 132)",
                fill: false,
                data: [
                    getRandomFactor(0, 300),
                    getRandomFactor(0, 300),
                    getRandomFactor(0, 300),
                    getRandomFactor(0, 300),
                    getRandomFactor(0, 300),
                    getRandomFactor(0, 300),
                    getRandomFactor(0, 300),
                ],
                yAxisID: 'y-axis-1',
            }, {
                label: 'post',
                borderColor: "rgb(54, 162, 235)",
                backgroundColor: "rgb(54, 162, 235)",
                fill: false,
                data: [
                    getRandomFactor(0, 20000),
                    getRandomFactor(0, 20000),
                    getRandomFactor(0, 20000),
                    getRandomFactor(0, 20000),
                    getRandomFactor(0, 20000),
                    getRandomFactor(0, 20000),
                    getRandomFactor(0, 20000),
                ],
                yAxisID: 'y-axis-1'
            }]
        };

        function getRandomFactor(min, max) {
            min = min === undefined ? 0 : min;
            max = max === undefined ? 1 : max;
            seed = (seed * 9301 + 49297) % 233280;
            return Math.round(min + (seed / 233280) * (max - min));
        }


        $(function() {
            var ctx = document.getElementById('lineChart').getContext('2d');

            window.myLine = Chart.Line(ctx, {
                data: lineChartData,
                options: {
                    responsive: true,
                    hoverMode: 'index',
                    stacked: false,
                    title: {
                        display: true,
                        text: 'Chart.js Line Chart - Multi Axis'
                    },
                    scales: {
                        yAxes: [{
                            type: 'linear', // only linear but allow scale type registration. This allows extensions to exist solely for log scale for instance
                            display: true,
                            position: 'left',
                            id: 'y-axis-1',
                        }, {
                            type: 'linear', // only linear but allow scale type registration. This allows extensions to exist solely for log scale for instance
                            display: true,
                            position: 'right',
                            id: 'y-axis-2',

                            // grid line settings
                            gridLines: {
                                drawOnChartArea: false, // only want the grid lines for one axis to show up
                            },
                        }],
                    }
                }
            });

            $("#randomizeData").on("click", function() {
                lineChartData.datasets.forEach(function(dataset) {
                    dataset.data = dataset.data.map(function() {
                        return getRandomFactor(-100, 100);
                    });
                });

                window.myLine.update();
            });
        });
    </script>
</head>
<body>
    <canvas id="lineChart"></canvas>

    <button id="randomizeData">Randomize Data</button>
</body>
</html>