var container = $('#systemPager');

function datefmt(date,fmt) {
    var o = {
        "M+" : date.getMonth()+1,                 //月份
        "d+" : date.getDate(),                    //日
        "h+" : date.getHours(),                   //小时
        "m+" : date.getMinutes(),                 //分
        "s+" : date.getSeconds(),                 //秒
        "q+" : Math.floor((date.getMonth()+3)/3), //季度
        "S"  : date.getMilliseconds()             //毫秒
    };
    if(/(y+)/.test(fmt))
        fmt=fmt.replace(RegExp.$1, (date.getFullYear()+"").substr(4 - RegExp.$1.length));
    for(var k in o)
        if(new RegExp("("+ k +")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
    return fmt;
}

Vue.filter('datafmt',function(date,fmt){
    var timedata=new Date(date);
    var o = {
        "M+" : timedata.getMonth()+1,                 //月份
        "d+" : timedata.getDate(),                    //日
        "h+" : timedata.getHours(),                   //小时
        "m+" : timedata.getMinutes(),                 //分
        "s+" : timedata.getSeconds(),                 //秒
        "q+" : Math.floor((timedata.getMonth()+3)/3), //季度
        "S"  : timedata.getMilliseconds()             //毫秒
    };
    if(/(y+)/.test(fmt))
        fmt=fmt.replace(RegExp.$1, (timedata.getFullYear()+"").substr(4 - RegExp.$1.length));
    for(var k in o)
        if(new RegExp("("+ k +")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
    return fmt;
})

var dig=new Vue({
    el: '.diglog',
    data:{
        diglogshow:false,
        modifyflag:1,   //1:新增  2：修改
        obj:{}
    },
    methods: {
        commit: function (keyword) {
            var flag = commitckeck();
            if (flag) {
                if (this.modifyflag == 1) {
                    var repet = ckeckrepit();
                    if (repet) {
                        var url = vm.getpath;
                        this.$http.post(url, dig.obj, {emulateJSON: true}).then(function (response) {
                            if (response.data.code == "200") {
                                dig.dighide();
                                alert("添加成功！");
                                vm.gopage();
                            }
                        });
                    }
                } else {
                    var url = vm.getpath + "/" + keyword;
                    this.$http.put(url, dig.obj, {emulateJSON: true}).then(function (response) {
                        if (response.data.code == "200") {
                            dig.dighide();
                            alert("修改成功！");
                            vm.gopage();
                        }
                    });
                }
            }
        },
        dighide: function () {
            dig.diglogshow = false;
        },
        digshow: function () {
            dig.diglogshow = true;
        }
    }
})


var vm=new Vue({
    el: '.content',
    data: {
        pagesize:10,
        pagenum:1,
        getpath:"",
        param:"",
        list:""
    },
    methods:{
        gopage:function(){
            var url=vm.getpath+'?page=' + vm.pagenum+"&size="+vm.pagesize+vm.param;
            this.$http.get(url).then(function(response){
                var msg=response.body;
                if(msg.data.pages>0&&(msg.data.pages<vm.pagenum||vm.pagenum<1)){
                    vm.pagenum=1;
                    vm.gopage();
                    return;
                }

                var sources = function () {
                    var result = [];
                    for (var i = 1; i < msg.data.total+1; i++) {
                        result.push(i);
                    }
                    return result;
                }();
                var options = {
                    dataSource: sources,
                    pageSize: vm.pagesize,
                    pageNumber: vm.pagenum,
                    pageRange: 2,
                    showNavigator: true,
                    showGoInput: true,
                    showGoButton: true,
                    activeClassName: 'systemPagerActive',
                    prevText: ' 上一页 ',
                    nextText: ' 下一页 ',
                    goButtonText: 'go',
                    //formatGoInput: '跳转到第<input class="J-paginationjs-go-pagenumber" id="gonum">页',
                    className: 'paginationjs-theme-yellow paginationjs-big',
                    //callback 每次翻页时的回调
                    callback: function (response, pagination) {
                        if(msg.data.list.length==0){
                            vm.list="";
                        }else{
                            vm.list=msg.data.list;
                        }
                    }
                };

                container.addHook('beforeInit', function () {
                    // 初始化的时候触发
                    window.console && console.log('beforeInit...');
                });
                // 将配制转给分页容器
                container.pagination(options);
                container.addHook('beforePageOnClick', function (event) {
                    var current = $(event.currentTarget);
                    var pageNumber = $.trim(current.attr('data-num'));
                    vm.pagenum=pageNumber;
                    vm.gopage();
                });
                container.addHook('beforeRender', function () {
                });
                container.addHook('beforePreviousOnClick', function () {
                    vm.pagenum-=1;
                    vm.gopage();
                });
                container.addHook('beforeNextOnClick', function () {
                    vm.pagenum+=1;
                    vm.gopage();
                });
                container.addHook('beforeGoButtonOnClick', function () {
                    var pageNumber = $('.J-paginationjs-go-pagenumber').val();
                    vm.pagenum=pageNumber;
                    vm.gopage();
                });
                $(".tt tbody").find("tr").each(function(){
                    $(this).index()%2!==0?$(this).addClass('even'):$(this).addClass('odd');
                })
            },function (response) {
                alert(JSON.stringify(response));
                //alert("error");
            })
        },
        deldata:function(id){
            if(confirm('是否确认删除？')){
                var url=vm.getpath+"/"+id;
                this.$http.delete(url).then(function(response){
                    if(response.body.code=='200'){
                        vm.gopage();
                    }
                })
            }
        },
        modify:function(id){
            dig.obj={};
            if(id!=null){
                dig.modifyflag=2
                var url=vm.getpath+"/"+id;
                this.$http.get(url).then(function(response){
                    if(response.body.code=='200'){
                        dig.obj=response.body.data;
                        dig.digshow();
                    }
                }).then(function(){
                    initfwb();
                })
            }else{
                dig.digshow();
                dig.modifyflag=1;
                setTimeout(initfwb,"500");
            }
        }
    }
})
