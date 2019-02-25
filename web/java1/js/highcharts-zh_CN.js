(function (H) {
    var protocol = window.location.protocol;
    var defaultOptionsZhCn = {
        lang: {
            contextButtonTitle: "",
            decimalPoint: ".",
            downloadJPEG: "Save JPEG",
            downloadPDF: "Save PDF",
            downloadPNG: "Save PNG",
            downloadSVG: "Save SVG",
            drillUpText: "",
            invalidDate: "",
            loading: "Wait...",
            months: ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"],
            noData: "No Data",
            numericSymbols: null,
            printChart: "",
            resetZoom: "",
            resetZoomTitle: "",
            shortMonths: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
            thousandsSep: ",",
            weekdays: ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"],
            rangeSelectorFrom: "",
            rangeSelectorTo: "",
            rangeSelectorZoom: "",
            zoomIn: "",
            zoomOut: ""
        },
        global: {
            canvasToolsURL: protocol + "//cdn.hcharts.cn/highcharts/modules/canvas-tools.js",
            VMLRadialGradientURL: protocol + +"//cdn.hcharts.cn/highcharts/gfx/vml-radial-gradient.png"
        },
        title: {text: "Title"},
        tooltip: {
            dateTimeLabelFormats: {
                millisecond: "%H:%M:%S.%L",
                second: "%H:%M:%S",
                minute: "%H:%M",
                hour: "%H:%M",
                day: "%Y-%m-%d",
                week: "%Y-%m-%d",
                month: "%Y-%m",
                year: "%Y"
            }, split: false
        },
        exporting: {url: protocol + "//export.highcharts.com.cn"},
        credits: {text: "Highcharts.com.cn", href: "https://www.highcharts.com.cn"},
        xAxis: {
            dateTimeLabelFormats: {
                millisecond: "%H:%M:%S.%L",
                second: "%H:%M:%S",
                minute: "%H:%M",
                hour: "%H:%M",
                day: "%Y-%m-%d",
                week: "%Y-%m",
                month: "%Y-%m",
                year: "%Y"
            }
        },
        rangeSelector: {
            inputDateFormat: "%Y-%m-%d",
            buttonTheme: {width: "auto", style: {fontSize: "12px", padding: "4px"}},
            buttons: [{type: "month", count: 1, text: ""}, {type: "month", count: 3, text: ""}, {
                type: "month",
                count: 6,
                text: ""
            }, {type: "ytd", text: "YTD"}, {type: "year", count: 1, text: ""}, {type: "all", text: ""}]
        },
        plotOptions: {
            series: {
                dataGrouping: {
                    dateTimeLabelFormats: {
                        millisecond: ["%Y-%m-%d %H:%M:%S.%L", "%Y-%m-%d %H:%M:%S.%L", " ~ %H:%M:%S.%L"],
                        second: ["%Y-%m-%d %H:%M:%S", "%Y-%m-%d %H:%M:%S", " ~ %H:%M:%S"],
                        minute: ["%Y-%m-%d %H:%M", "%Y-%m-%d %H:%M", " ~ %H:%M"],
                        hour: ["%Y-%m-%d %H:%M", "%Y-%m-%d %H:%M", " ~ %H:%M"],
                        day: ["%Y-%m-%d", "%Y-%m-%d", " ~ %Y-%m-%d"],
                        week: ["%Y-%m-%d", "%Y-%m-%d", " ~ %Y-%m-%d"],
                        month: ["%Y-%m", "%Y-%m", " ~ %Y-%m"],
                        year: ["%Y", "%Y", " ~ %Y"]
                    }
                }
            },
            ohlc: {
                tooltip: {
                    split: false,
                    pointFormat: '<span style="color:{point.color}"></span> <b> {series.name}</b><br/>' + "{point.open}<br/>" + "{point.high}<br/>" + "{point.low}<br/>" + "point.close}<br/>"
                }
            },
            candlestick: {
                tooltip: {
                    split: false,
                    pointFormat: '<span style="color:{point.color}"></span> <b> {series.name}</b><br/>' + "{point.open}<br/>" + "{point.high}<br/>" + "{point.low}<br/>" + "point.close}<br/>"
                }
            }
        }
    };
    H.setOptions(defaultOptionsZhCn)
})(Highcharts);