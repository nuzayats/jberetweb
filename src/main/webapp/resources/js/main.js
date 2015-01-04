function onJobExecutionRowClick(e) {
    e.parentNode.children[0].children[1].click();
}

function onSelectAction(e) {
    var type = e.children[e.selectedIndex].getAttribute('value');

    var executionId;
    if (type == 'RESTART') {
        executionId = getExecutionId(e);
        window.open('startJob.xhtml?restart=' + executionId, 'Restart Job', 'width=640,height=480');
        return false;
    }
    if (type == 'RE_EXECUTE') {
        executionId = getExecutionId(e);
        window.open('startJob.xhtml?reexec=' + executionId, 'Start Job', 'width=640,height=480');
        return false;
    }

    return true;
}

function getExecutionId(elem) {
    return elem.parentNode.parentNode.children[0].children[0].innerHTML;
}