<%@ page pageEncoding="utf-8"%>
<!-- Modal -->
<div id="myModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Send to friend</h4>
      </div>
      <div class="modal-body">
      	<div class="form-group">
        	<label>Sender Email</label>
        	<input id="sender" class="form-control">
        </div>
        <div class="form-group">
        	<label>Recipient Email</label>
        	<input id="email" class="form-control">
        </div>
        <div class="form-group">
        	<label>Comments</label>
        	<textarea id="comments" class="form-control" rows="3"></textarea>
        </div>
        <input  type="hidden" id="id">
      </div>
      <div class="modal-footer">
        <button class="btn btn-default btn-send">Send</button>
      	<button class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>

  </div>
</div>