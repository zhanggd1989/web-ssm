var gulp = require('gulp');
var plugins = require('gulp-load-plugins')();

gulp.task('default',['build','watch']);
gulp.task('build',['copy']);

//js复制任务
gulp.task('copy', function () {
    gulp.src('node_modules/jquery/dist/jquery.min.js')//复制jquery
        .pipe(gulp.dest('statics/js/jquery/'));
    gulp.src('node_modules/bootstrap/dist/**')// 复制bootstrap
        .pipe(gulp.dest('statics/js/bootstrap/'));
})
//css压缩任务
/*gulp.task('css', function () {
    var cssSrc = ['staticDev/css/!*.css','!staticDev/css/!*.min.css'],
        cssDst = './static-dev/css/';
    gulp.src(cssSrc) //要压缩的文件
        .pipe(plugins.minifyCss()) //压缩
        .pipe(gulp.dest(cssDst));  //压缩后的文件路径

    var cssDemoSrc = ['staticDev/demo/css/!*.css','!staticDev/demo/css/!*.min.css'],
        cssDemoDst = './static-dev/demo/css/';
    gulp.src(cssDemoSrc) //要压缩的文件
        .pipe(plugins.concat('main.css'))//合并文件，文件名为main.css
        .pipe(plugins.minifyCss()) //压缩
        .pipe(plugins.rename({ suffix: '.min' }))  //改字，加上min标志
        .pipe(gulp.dest(cssDemoDst));  //压缩后的文件路径
});*/
//js压缩任务
/*gulp.task('js', function () {
    var jsSrc = ['staticDev/js/!*.js','!staticDev/js/!*.min.js'],
        jsDst = 'static-dev/js/';
    gulp.src(jsSrc)
        .pipe(plugins.uglify())
        .pipe(gulp.dest(jsDst));

    var jsDemoSrc = ['staticDev/demo/js/!*.js','!staticDev/demo/js/!*.min.js'],
        jsDemoDst = 'static-dev/demo/js/';
    gulp.src(jsDemoSrc)
        .pipe(plugins.concat('main.jf'))
        .pipe(plugins.uglify())
        .pipe(plugins.rename({ suffix: '.min' }))
        .pipe(gulp.dest(jsDemoDst));
});*/
//img压缩
/*gulp.task('img', function () {
    var imgSrc = 'staticDev/images/!*',
        imgDst = 'static-dev/images/';
    gulp.src(imgSrc)
        .pipe(plugins.imagemin())
        .pipe(gulp.dest(imgDst));

    var imgDemoSrc = 'staticDev/demo/images/!*',
        imgDemoDst = 'static-dev/demo/images/';
    gulp.src(imgDemoSrc)
        .pipe(plugins.imagemin())
        .pipe(gulp.dest(imgDemoDst));
});*/
//监视
gulp.task('watch', function () {
    gulp.watch(['statics/js/*.js', 'statics/css/*.css'], ['build']);
});