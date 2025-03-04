function realtimeClock() {
    $('#time').text(getTimeStamp());
    setTimeout("realtimeClock()", 1000);
}

function getTimeStamp() {
    var d = new Date();

    var s = leadingZeros(d.getFullYear(), 4) + '-' +
        leadingZeros(d.getMonth() + 1, 2) + '-' +
        leadingZeros(d.getDate(), 2) + ' / ' +

        leadingZeros(d.getHours(), 2) + ':' +
        leadingZeros(d.getMinutes(), 2) + ':' +
        leadingZeros(d.getSeconds(), 2);

    return s;
}

function leadingZeros(n, digits) {
    var zero = '';
    n = n.toString();

    if (n.length < digits) {
        for (i = 0; i < digits - n.length; i++) {
            zero += '0';
        }
    }
    return zero + n;
}

var url = location.href;
var id = url.substring(url.lastIndexOf('/') + 1);

window.chartColors = {
    blue: "rgb(54, 162, 235)",
    green: "rgb(75, 192, 192)",
    grey: "rgb(201, 203, 207)",
    orange: "rgb(255, 159, 64)",
    purple: "rgb(153, 102, 255)",
    red: "rgb(255, 99, 132)",
    yellow: "rgb(255, 205, 86)"
};

var randomScalingFactor = function() {
    return Math.round(Math.random() * 100);
};

function getRandomData() {
    return {
        totalDisk: Math.round(Math.random() * 100),
        usedDisk: Math.round(Math.random() * 100),
        freeDisk: Math.round(Math.random() * 100),
        totalDisk: Math.round(Math.random() * 100),
        usedMemory: Math.round(Math.random() * 16),
        freeMemory: Math.round(Math.random() * 16),
        usedMemory: Math.round(Math.random() * 16),
        totalCpu: Math.round(Math.random() * 100),
        userCpu: Math.round(Math.random() * 100),
        systemCpu: Math.round(Math.random() * 100),
        idleCpu: Math.round(Math.random() * 100)
    }
}

var ctx;
var memoryChart;
var config = {
    type: 'line',
    data: {
        labels: [],
        datasets: [{
            lineTension: 0,
            label: "used memory",
            backgroundColor: 'rgba(0,0,0,0)',
            borderColor: window.chartColors.red,
            data: [],
            fill: true
        }]
    },
    options: {
        responsive: true,
        tooltips: {
            mode: 'index',
            intersect: false,
        },
        hover: {
            mode: 'nearest',
            intersect: true
        },
        scales: {
            xAxes: [{
                display: true
            }],
            yAxes: [{
                ticks: {
                    beginAtZero: true
                },
                display: true
            }]
        }
    }
};

function memoryUpdate(data) {
    if (config.data.datasets[0].data.length >= 7) {
        config.data.labels.shift();
        config.data.datasets.forEach(function(data) {
            data.data.shift();
        });
    }
    config.data.labels.push(getTimeStamp()); // 이곳에 시간을 넣어주시면 됩니다.

    config.data.datasets[0].data.push(data.usedMemory);

    window.memoryChart.update();
}

$(document).ready(function() {
    ctx = document.getElementById("memoryChartLine").getContext("2d");

    memoryChart = new Chart(ctx, config);
    memoryUpdate({usedMemory: 4});
    setInterval(function() {
        $.ajax({
            type: 'GET',
            url: '/monitoring/detail',
            data: {
                "id": id
            },
            success: function(res) {
                var data = JSON.parse(res);
                memoryUpdate(data);
            },
            error: function(err) {
                console.log('cannot receive status model.');
                memoryUpdate(getRandomData());

            }
        });
    }, 3000);
});
