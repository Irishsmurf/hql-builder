function primeuifactory(prefix) {
	console.log('primeuifactory['+prefix+']');
	$(prefix+'.puiinputtext').puiinputtext({width:'auto'});
	$(prefix+'.puiinputtextarea').puiinputtextarea({width:'auto'});
	$(prefix+'.puidropdown').puidropdown({width:'auto',filter:true});
	$(prefix+'.puiaccordion').puiaccordion({});
	$(prefix+'.puipassword').puipassword({width:'auto'});
	$(prefix+'.puispinner').puispinner({width:'auto'});
	$(prefix+'.puilistbox').puilistbox({width:'auto'}).parent().parent().height(200);
	$(prefix+'.puiautocomplete').puiautocomplete({width:'auto'});
	$(prefix+'.puibreadcrumb').puibreadcrumb({});
	$(prefix+'.puibutton').puibutton({});
	$(prefix+'.puicheckbox').puicheckbox({});
	$(prefix+'.puifieldset').puifieldset({});
	$(prefix+'.puigalleria').puigalleria({});
	$(prefix+'.puilightbox').puilightbox({});
	$(prefix+'.puimenu').puimenu({});
	$(prefix+'.puimenubar').puimenubar({});
	$(prefix+'.puicontextmenu').puicontextmenu({});
	$(prefix+'.puislidemenu').puislidemenu({});
	$(prefix+'.puitieredmenu').puitieredmenu({});
	$(prefix+'.puipaginator').puipaginator({});
	$(prefix+'.puipanel').puipanel({});
	$(prefix+'.puipicklist').puipicklist({showSourceControls:false,showTargetControls:false,filter:true});
	$(prefix+'.puiprogressbar').puiprogressbar({});
	$(prefix+'.puiradiobutton').puiradiobutton({});
	$(prefix+'.puisticky').puisticky({});
	$(prefix+'.puitabview').puitabview({});
	$(prefix+'.puigrowl').puigrowl({});
	$(prefix+'.puinotifytop').puinotify({easing:'easeInOutCirc',position:'top'});
	$(prefix+'.puinotifybottom').puinotify({easing:'easeInOutCirc',position:'bottom'});
	$(prefix+'.puidropdownfiltered').puidropdown({width:'auto',filter:true,filterMatchMode:'contains'});
}
$(document).ready(function() {
	primeuifactory('');
});