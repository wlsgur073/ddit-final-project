<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!-- The Modal -->
  <div class="modal" id="anyWhereSpoon">
    <div class="modal-dialog">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <h5 class="modal-title"><img src="https://img.icons8.com/ios/20/000000/spoon.png"/>&nbsp Spoon</h5>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
            <div class="x_panel">
              <div class="x_content">
                <form class="form-horizontal form-label-left">

                  <div class="form-group row ">
                    <label class="control-label col-md-3 col-sm-3 ">프로젝트 명</label>
                    <div class="col-md-9 col-sm-9 ">
                      <input type="text" class="form-control" readonly="readonly" value="">
                    </div>
                  </div>
                  <div class="form-group row">
                    <label class="control-label col-md-3 col-sm-3 ">담당자</label>
                    <div class="col-md-9 col-sm-9 ">
                      <input type="text" class="form-control" readonly="readonly" placeholder="담당자를 선택해주세요." value="">
                    </div>
                  </div>
                  <div class="form-group row">
                    <label class="control-label col-md-3 col-sm-3 ">업무 명 </label>
                    <div class="col-md-9 col-sm-9 ">
                      <input type="text" class="form-control" readonly="readonly" placeholder="업무를 선택해주세요." value="">
                    </div>
                  </div>


                  
                  <h6 class="collaboSpButton">Spoon 할 콜라보 프로젝트를 선택하세요.</h6>
                  <div class="form-group row">
                    <label class="control-label col-md-3 col-sm-3 ">콜라보</label>
                    <div class="col-md-9 col-sm-9 ">
                      <select class="form-control">
                        <option>김진혁 앙골라 국제 결혼 프로젝트</option>
                        <option>김진혁 르완다 국제 결혼 프로젝트</option>
                        <option>김진혁 아프리카 장가보내기 프로젝트</option>
                      </select>
                    </div>
                  </div>
                </form>
              </div>
            </div>

        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
          <button type="submit" class="btn btn-primary">확인</button>
          <button type="button" class="btn btn-danger" data-dismiss="modal">취소</button>
        </div>
        
      </div>
    </div>
  </div>
  
</div>