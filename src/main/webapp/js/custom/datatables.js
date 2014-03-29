/**
 * Created by kyle on 2014/03/29.
 */
var oTable;
var oStepsTable;
var giRedraw = false;

$(document).ready(function () {

    /* Add a click handler to the rows - this could be used as a callback */
    $("#example tbody").click(function (event) {
        $(oTable.fnSettings().aoData).each(function () {
            $(this.nTr).removeClass('row_selected');
        });

        var selected = $(event.target.parentNode);
        selected.addClass('row_selected');

        var executionId = selected.children('td:first').text();
        if (!executionId) {
            return;
        }

        updateJobExecutionInfo(executionId);
        updateStepsTable(executionId);
    });

    /* Add a click handler for the delete row */
    $('#delete').click(function () {
        var anSelected = fnGetSelected(oTable);
        oTable.fnDeleteRow(anSelected[0]);
    });

    /* Init the table */
    oTable = $('#example').dataTable({
        "bFilter": false,
        "sPaginationType": "full_numbers",
        "aaSorting": [
            [ 0, "desc" ]
        ],
        "aoColumns": [
            { "bSortable": true },
            { "bSortable": false },
            { "bSortable": false },
            { "bSortable": false },
            { "bSortable": false }
        ],
        "bProcessing": true,
        "bServerSide": true,
        "sAjaxSource": "webapi/JobExecutionReportService"
    });

});

/* Get the rows which are currently selected */
function fnGetSelected(oTableLocal) {
    var aReturn = new Array();
    var aTrs = oTableLocal.fnGetNodes();

    for (var i = 0; i < aTrs.length; i++) {
        if ($(aTrs[i]).hasClass('row_selected')) {
            aReturn.push(aTrs[i]);
        }
    }
    return aReturn;
}

$.fn.dataTableExt.oApi.fnReloadAjax = function (oSettings, sNewSource, fnCallback, bStandingRedraw) {
    // DataTables 1.10 compatibility - if 1.10 then versionCheck exists.
    // 1.10s API has ajax reloading built in, so we use those abilities
    // directly.
    if ($.fn.dataTable.versionCheck) {
        var api = new $.fn.dataTable.Api(oSettings);

        if (sNewSource) {
            api.ajax.url(sNewSource).load(fnCallback, !bStandingRedraw);
        }
        else {
            api.ajax.reload(fnCallback, !bStandingRedraw);
        }
        return;
    }

    if (sNewSource !== undefined && sNewSource !== null) {
        oSettings.sAjaxSource = sNewSource;
    }

    // Server-side processing should just call fnDraw
    if (oSettings.oFeatures.bServerSide) {
        this.fnDraw();
        return;
    }

    this.oApi._fnProcessingDisplay(oSettings, true);
    var that = this;
    var iStart = oSettings._iDisplayStart;
    var aData = [];

    this.oApi._fnServerParams(oSettings, aData);

    oSettings.fnServerData.call(oSettings.oInstance, oSettings.sAjaxSource, aData, function (json) {
        /* Clear the old information from the table */
        that.oApi._fnClearTable(oSettings);

        /* Got the data - add it to the table */
        var aData = (oSettings.sAjaxDataProp !== "") ?
            that.oApi._fnGetObjectDataFn(oSettings.sAjaxDataProp)(json) : json;

        for (var i = 0; i < aData.length; i++) {
            that.oApi._fnAddData(oSettings, aData[i]);
        }

        oSettings.aiDisplay = oSettings.aiDisplayMaster.slice();

        that.fnDraw();

        if (bStandingRedraw === true) {
            oSettings._iDisplayStart = iStart;
            that.oApi._fnCalculateEnd(oSettings);
            that.fnDraw(false);
        }

        that.oApi._fnProcessingDisplay(oSettings, false);

        /* Callback user function - for event handlers etc */
        if (typeof fnCallback == 'function' && fnCallback !== null) {
            fnCallback(oSettings);
        }
    }, oSettings);
};

function updateJobExecutionInfo(executionId) {
    var sAjaxResource = getJobExecutionServicePath(executionId);

    $.ajax({
        cache: false,
        url: sAjaxResource,
        dataType: "json",
        success: function (data) {
            var jobParams = data['jobparameters'];
            var splitted = jobParams.split('\n');

            var html = '<ul>';
            for (var i = 0; i < splitted.length; i++) {
                if (!splitted[i]) {
                    continue;
                }
                html += '<li>' + splitted[i] + '</li>';
            }
            html += '</ul>';

            $('#executionInfo').html(html);
        }
    });

}

function updateStepsTable(executionId) {
    var sAjaxResource = getStepExecutionReportServicePath(executionId);

    if (oStepsTable === undefined) {
        oStepsTable = $('#steps').dataTable({
            "bPaginate": false,
            "bSort": false,
            "bInfo": false,
            "bFilter": false,
            "bProcessing": true,
            "bServerSide": true,
            "sAjaxSource": sAjaxResource
        });
        return;
    }

    oStepsTable.fnReloadAjax(sAjaxResource);
}

function getStepExecutionReportServicePath(executionId) {
    return "webapi/StepExecutionReportService/" + executionId;
}

function getJobExecutionServicePath(executionId) {
    return "webapi/JobExecutionService/" + executionId;
}