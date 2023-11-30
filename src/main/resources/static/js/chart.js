function getGaugeChart() {
    const ctx = document.getElementById('gaugeChart').getContext('2d');

    const gradientSegment = ctx.createLinearGradient(0, 0, 700, 0);
    gradientSegment.addColorStop(0, 'red');
    gradientSegment.addColorStop(0.7, 'yellow');
    gradientSegment.addColorStop(1, 'green');

    const data = {
        labels: ['일일 미터 사용량', '일일 미터 남은 사용량'],
        datasets: [{
            label: 'Weekly Meter Usage',
            data: [usageForDay, 10 - usageForDay],
            backgroundColor: [
                gradientSegment,
                'rgba(0, 0, 0, 0.2)'
            ],
            borderColor: [
                gradientSegment,
                'rgba(0, 0, 0, 0.2)'
            ],
            borderWidth: 1,
            cutout: '50%', // 'cutoutPercentage' 대신 'cutout' 사용
            circumference: 180,
            rotation: 270
        }]
    };

    const gaugeChartText = {
        id: 'gaugeChartText',
        afterDatasetsDraw(chart, args, pluginOptions) {
            const {
                ctx, data, chartArea: {
                    top, bottom, left, right, width, height
                }, scales: { r }
            } = chart;

            ctx.save(); // 그래픽 컨텍스트 상태 저장
            const xCoor = chart.getDatasetMeta(0).data[0].x;
            const yCoor = chart.getDatasetMeta(0).data[0].y;
            const meter = data.datasets[0].data[0];

            // 텍스트 레이블을 그리는 함수
            function textLabel(text, x, y, fontSize, textBaseline, textAlign) {
                ctx.font = `${fontSize}px sans-serif`;
                ctx.fillStyle = '#666';
                ctx.textBaseline = textBaseline;
                ctx.textAlign = textAlign;
                ctx.fillText(text, x, y);
            }

            // 텍스트 레이블을 그립니다.
            textLabel("0", left, yCoor + 5, 20, 'top', 'left');
            textLabel("10", right, yCoor + 5, 20, 'top', 'right');
            textLabel(meter, xCoor, yCoor, 25, 'bottom', 'center');
        }
    }

    const config = {
        type: 'doughnut',
        data,
        options: {
            aspectRatio: 1.5,
            plugins: {
                legend: {
                    display: false
                },
                title: { // 제목 설정 부분
                    display: true,
                    text: titleForDay, // titleForDay 변수에 저장된 텍스트를 사용
                    position: 'top',
                    padding: {
                        top: 20, // 상단 패딩 크기 조정
                        bottom: 0  // 하단 패딩 크기 조정
                    },
                    font: {
                        size: 16,
                        weight: 'bold'
                    }
                }
            }
        },
        plugins: [gaugeChartText]
    };

    const myChart = new Chart(
        document.getElementById('gaugeChart'),
        config
    );
}

function getBarChart() {
    // 그래프를 그릴 Canvas 요소 가져오기
    const ctx = document.getElementById('barChart').getContext('2d');

    // 데이터 포맷
    const meterTs = weeklyMeterList.map(function (item) {
        // 날짜 데이터를 'yyyy-MM-dd' 형식으로 변환
        const date = new Date(item.meterTs);
        const year = date.getFullYear();
        const month = String(date.getMonth() + 1).padStart(2, '0'); // 월은 0부터 시작하므로 +1, 두 자리로 맞추기
        const day = String(date.getDate()).padStart(2, '0'); // 날짜, 두 자리로 맞추기
        return `${year}-${month}-${day}`;
    });

    const meter = weeklyMeterList.map(function (item) {
        return item.meter; // 총량 데이터
    });

    // Chart.js 그래프 설정
    const myChart = new Chart(ctx, {
        type: 'bar', // 막대 그래프
        data: {
            labels: meterTs, // X 축 레이블 (날짜)
            datasets: [{
                label: '2시간당 미터량',
                data: meter, // Y 축 데이터 (미터 총량)
                backgroundColor: 'rgba(75, 192, 192, 0.2)', // 막대 색상
                borderColor: 'rgba(75, 192, 192, 1)', // 막대 테두리 색상
                borderWidth: 1 // 테두리 두께
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            },
            plugins: {
                title: {
                   text: title, // title 변수에 저장된 텍스트를 사용
                   display: true,
                   padding: {
                       top: 20, // 원하는 상단 패딩 크기 설정
                       bottom: 20 // 원하는 하단 패딩 크기 설정
                   },
                   font: {
                       size: 16, // 원하는 폰트 크기 설정
                       weight: 'bold'
                   }
               },
               legend: {
                    display: false // 범례 숨기기
               }
           }
        }
    });
}

function getDonutChart() {
    // 그래프를 그릴 Canvas 요소 가져오기
    const ctx = document.getElementById('donutChart').getContext('2d');

    // 데이터 포맷
    const meterTs = weeklyMeterList.map(function (item) {
        // 날짜 데이터를 'yyyy-MM-dd' 형식으로 변환
        const date = new Date(item.meterTs);
        const year = date.getFullYear();
        const month = String(date.getMonth() + 1).padStart(2, '0'); // 월은 0부터 시작하므로 +1, 두 자리로 맞추기
        const day = String(date.getDate()).padStart(2, '0'); // 날짜, 두 자리로 맞추기
        return `${year}-${month}-${day}`;
    });

    const meter = weeklyMeterList.map(function (item) {
        return item.meter; // 총량 데이터
    });

    const backgroundColors = [];

    for (let i = 0; i < meter.length; i++) {
        const randomColor = `rgba(${Math.random() * 255}, ${Math.random() * 255}, ${Math.random() * 255}, 0.2)`;
        backgroundColors.push(randomColor);
    }

    const data = {
       labels: meterTs, // X 축 레이블 (날짜)
       datasets: [{
           data: meter, // Y 축 데이터 (미터 총량)
           backgroundColor: backgroundColors, // 도넛 차트의 섹션 색상 (동적으로 생성한 배열 사용)
           borderWidth: 1 // 테두리 두께
       }]
    };

    const donutChartText = {
        id: 'donutChartText',
        afterDraw(chart, args, options) {
            const {
                ctx, data, chartArea
            } = chart;

            // 텍스트 스타일 설정
            ctx.fillStyle = '#000';
            ctx.font = '14px Arial';
            ctx.textAlign = 'center';
            ctx.textBaseline = 'middle';

            // 데이터셋 반복 및 각 데이터 포인트 위에 meterTs 그리기
            chart.data.datasets.forEach(function (dataset, datasetIndex) {
                const meta = chart.getDatasetMeta(datasetIndex);

                if (!meta.hidden) {
                    meta.data.forEach(function (element, index) {
                        // 데이터 포인트 위치 가져오기
                        const model = element._model;

                        // 해당 데이터 포인트의 meterTs 그리기
                        const meterTsText = meterTs[index];
                        ctx.fillText(meterTsText, model.x, model.y - 20); // 원하는 위치에 맞게 조정
                    });
                }
            });
        }
    }

    const config = {
        type: 'doughnut', // 도넛 차트로 변경
        data,
        options: {
            plugins: {
                title: {
                    text: title, // title 변수에 저장된 텍스트를 사용
                    display: true,
                    padding: {
                        top: 20, // 원하는 상단 패딩 크기 설정
                        bottom: 20 // 원하는 하단 패딩 크기 설정
                    },
                    font: {
                        size: 16, // 원하는 폰트 크기 설정
                        weight: 'bold'
                    }
                },
                legend: {
                    display: false // 범례 숨기기
                }
            }
       }
//       plugins: [donutChartText]
    };

    const myChart = new Chart(
        document.getElementById('donutChart'),
        config
    );
}