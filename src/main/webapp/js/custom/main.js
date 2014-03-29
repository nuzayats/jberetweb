/**
 * Created by kyle on 2014/03/29.
 */
$(document).ready(function () {
    $('body').layout({
        stateManagement__enabled: true,
        center__size: '50%',
        south__size: '50%',
        applyDemoStyles: true,
        center__paneSelector:	".outer-center",
        south__paneSelector:	".outer-south",
        south__childOptions: {
            west__size : '40%',
            center__size : '60%',
            west__paneSelector: ".inner-west",
            center__paneSelector: ".inner-center",
            applyDemoStyles: true
        }
    });
});

