function onJobExecutionRowClick(e) {
   e.parentNode.children[0].children[1].click();
}

function onSelectAction(e) {
    if (e.children[e.selectedIndex].getAttribute('value') == 'RESTART') {
        var executionId = e.parentNode.parentNode.children[0].children[0].innerHTML;
        window.open('startJob.xhtml?restart=' + executionId, 'Restart Job', 'width=640,height=480');
        return false;
    }
    return true;
}