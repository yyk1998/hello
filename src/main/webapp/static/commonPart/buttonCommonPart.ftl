<script type="text/javascript" src="/static/asserts/js/jquery-3.2.1.slim.min.js"></script>
<script type="text/javascript" src="/static/asserts/js/popper.min.js"></script>
<script type="text/javascript" src="/static/asserts/js/bootstrap.min.js"></script>

<!-- Icons -->
<script type="text/javascript" src="/static/asserts/js/feather.min.js"></script>
<script>
    feather.replace()
</script>
<script type="text/javascript" src="/static/asserts/js/Chart.min.js"></script>
<script>
    var ctx = document.getElementById("myChart");
    var myChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"],
            datasets: [{
                data: [15339, 21345, 18483, 24003, 23489, 24092, 12034],
                lineTension: 0,
                backgroundColor: 'transparent',
                borderColor: '#007bff',
                borderWidth: 4,
                pointBackgroundColor: '#007bff'
            }]
        },
        options: {
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: false
                    }
                }]
            },
            legend: {
                display: false,
            }
        }
    });
</script>