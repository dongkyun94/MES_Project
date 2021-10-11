<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="kr">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Dashboard - SB Admin</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link href="css/styles.css" rel="stylesheet" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js" crossorigin="anonymous"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    </head>
    <body class="sb-nav-fixed">
        <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
            <!-- Navbar Brand-->
            <a class="navbar-brand ps-3" href="/MES">MES</a>
            <!-- Sidebar Toggle-->
            <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!"><i class="fas fa-bars"></i></button>
            <!-- Navbar Search-->
            <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0">
                <div class="input-group">
                    <input class="form-control" type="text" placeholder="Search for..." aria-label="Search for..." aria-describedby="btnNavbarSearch" />
                    <button class="btn btn-primary" id="btnNavbarSearch" type="button"><i class="fas fa-search"></i></button>
                </div>
            </form>
            <!-- Navbar-->
            <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="#!">Settings</a></li>
                        <li><a class="dropdown-item" href="#!">Activity Log</a></li>
                        <li><hr class="dropdown-divider" /></li>
                        <c:if test="${! empty authUser }">
                        <li><a class="dropdown-item" href="changePwd.do">changePassword</a></li>
                        <li><a class="dropdown-item" href="logout.do">Logout</a></li>
                        </c:if>
                        <c:if test="${empty authUser }">
                        <li><a class="dropdown-item" href="login.do">Login</a></li>
                        <li><a class="dropdown-item" href="join.do">Join</a></li>
                        </c:if>
                    </ul>
                </li>
            </ul>
        </nav>
        <div id="layoutSidenav">
            <div id="layoutSidenav_nav">
                <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
                    <div class="sb-sidenav-menu">
                        <div class="nav">
                            <div class="sb-sidenav-menu-heading">Core</div>
                            <a class="nav-link" href="/MES/main.do">
                                <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                                Dashboard
                            </a>
                            <div class="sb-sidenav-menu-heading">주 메뉴</div>
                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#basicDataLayouts" aria-expanded="false" aria-controls="collapseLayouts">
                                <div class="sb-nav-link-icon"><i class="fas fa-table"></i></div>
                                기초데이터관리
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                            <div class="collapse" id="basicDataLayouts" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav">
                                    <a class="nav-link" href="factorylist.do">공장관리</a>
                                    <a class="nav-link" href="linelist.do">라인관리</a>
                                    <a class="nav-link" href="#">설비관리</a>
                                    <a class="nav-link" href="itemlist.do">품목관리</a>
                                </nav>
                            </div>
                            <a class="nav-link collapsed" href="orderlist.do" data-bs-toggle="collapse" data-bs-target="#orderLayouts" aria-expanded="false" aria-controls="collapseLayouts">
                                <div class="sb-nav-link-icon"><i class="fas fa-industry"></i></div>
                                생산관리
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                            <div class="collapse" id="orderLayouts" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav">
                                    <a class="nav-link" href="orderlist.do">주문</a>
                                </nav>
                            </div>
                            <div class="collapse" id="orderLayouts" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav">
                                    <a class="nav-link" href="productionlist.do">생산지시</a>
                                </nav>
                            </div> 
                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#equipLayouts" aria-expanded="false" aria-controls="collapseLayouts">
                                <div class="sb-nav-link-icon"><i class="fas fa-bell"></i></div>
                                설비알람 설정
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                            <div class="collapse" id="equipLayouts" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav">
                                    <a class="nav-link" href="eqmasterlist.do">마스터테이블관리</a>
                                </nav>
                            </div>
                            <div class="sb-sidenav-menu-heading">Addons</div>
                            <a class="nav-link" href="charts.html">
                                <div class="sb-nav-link-icon"><i class="fas fa-chart-area"></i></div>
                                Charts
                            </a>
                            <a class="nav-link" href="tables.html">
                                <div class="sb-nav-link-icon"><i class="fas fa-table"></i></div>
                                Tables
                            </a>
                        </div>
                    </div>
                    <div class="sb-sidenav-footer">
                        <div class="small">Logged in as:</div>
                        ${authUser.name }
                    </div>
                </nav>
            </div>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">생산지시</h1>
                        <c:if test="${! empty authUser }">
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active">${authUser.name }님, 안녕하세요</li>
                        </ol>
                        </c:if>
                        <c:if test="${ empty authUser }">
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active">생산지시</li>
                        </ol>
                        </c:if>
                        <hr />
                        <!-- 모달을 열기 위한 버튼 -->
                        <div class="row mb-2">
                        	<div class="col">
                        	<c:if test="${! empty orderNoList }">
	                        	<button type="button" class="btn btn-primary btn-md float-end" data-bs-toggle="modal" data-bs-target="#myModal">
									생산지시
								</button>
							</c:if>
							</div>
						</div>
						<!-- 모달 영역 -->
						<div class="modal fade" id="myModal" tabindex="-1" aria-labelledby="myModalLabel">
							<div class="modal-dialog modal-xl" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<h4 class="modal-title" id="myModalLabel">생산지시</h4>
										<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
									</div>
									<div class="modal-body">
									<!-- 입력 폼-->
										<form action="productioninsert.do" method="post">
					                    	<div class="row g-3">
						                    	<div class="col-sm-6">
						                    		<label for="comp_cd" class="form-label">회사코드</label>
						                        		<select class="form-control" id="comp_cd" name="comp_cd">
						                            		<option value="1">1</option>
						                            		<option value="2">2</option>
						                        		</select>
						                		</div>
						                		<div class="col-sm-6">
						                    		<label for="plant_cd" class="form-label">공장코드</label>
						                        		<select class="form-control" id="plant_cd" name="plant_cd">
						                            		<option value="1000">1000</option>
						                            		<option value="1001">1001</option>
						                        		</select>
						                		</div>
						                		<div class="col-6">
						                    		<label for="order_no" class="form-label">주문번호</label>
						                        		<select class="form-control" id="order_no" name="order_no">
						                            		<c:forEach var="orderNo" items ="${orderNoList }">
						                            		<option value="${orderNo}">${orderNo}</option>
						                            		</c:forEach>
						                        		</select>
						                        </div>
						                		<div class="col-sm-6">
						                    		<label for=line_cd class="form-label">라인코드</label>
					                        		<select class="form-control" id="line_cd" name="line_cd">
						                            	<option value="LINE_1">LINE_1</option>
						                            	<option value="LINE_2">LINE_2</option>
						                        	</select>
						                		</div>
						                		<div class="col-sm-">
						                    		<label for=equip_cd class="form-label">설비코드</label>
					                        		<select class="form-control" id="equip_cd" name="equip_cd">
						                            	<option value="OP01">OP01</option>
						                            	<option value="OP02">OP02</option>
						                        	</select>
						                		</div>
						                		<hr />
						                    	<div class="col-sm-6">
						                    		<label for="start_dt" class="form-label">작업시작일</label>
					                        		<input class="form-control" type="date" id="start_dt" name="start_dt">
						                		</div>
						                		<div class="col-sm-6">
						                    		<label for=start_shift class="form-label">시작작업조</label>
					                        		<select class="form-control" id="start_shift" name="start_shift">
						                            		<option value="주간">주간</option>
						                            		<option value="야간">야간</option>
						                        	</select>
						                		</div>
						                    	<div class="col-sm-6">
						                    		<label for="end_dt" class="form-label">작업종료일</label>
					                        		<input class="form-control" type="date" id="end_dt" name="end_dt">
						                		</div>
						                		<div class="col-sm-6">
						                    		<label for=end_shift class="form-label">종료작업조</label>
					                        		<select class="form-control" id="end_shift" name="end_shift">
						                            		<option value="주간">주간</option>
						                            		<option value="야간">야간</option>
						                        	</select>
						                		</div>
						                		<div class="col-sm-6">
						                    		<label for=flag_end class="flag_end">상태</label>
					                        		<select class="form-control" id="flag_end" name="flag_end">
						                            		<option value="대기">대기</option>
						                            		<option value="작업중">작업중</option>
						                            		<option value="종료">완료</option>
						                        	</select>
						                		</div>
						                		<div class="col-sm-6">
						                    		<label for=plan_qty class="form-label">계획수량</label>
					                        		<input class="form-control" type="text" id="plan_qty" name="plan_qty">
						                		</div>
						                		<div class="col-12">
						                    		<label for="remark">비고(특이사항)</label>
						                    		<div id="provision">
						                        		<textarea class="form-control" rows="8" style="resize:none" name ="remark"></textarea>
						                    		</div>
						                		</div>
						                		<div class ="text-center">
						                			<input type="submit" class = "btn btn-primary" value="등록">
						                		</div>
						                    </div>
					                    </form>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-default" data-bs-dismiss="modal">취소</button>
									</div>
								</div>
							</div>
						</div>
						<!-- 데이터 테이블 영역 -->
                    <div class="card mb-4">
                            <div class="card-header">
                                <i class="fas fa-table me-1"></i>
                                주문목록
                            </div>
                            
                    	<div class="card-body">
                                <table id="datatablesSimple">
                                    <thead>
                                        <tr>
                                            <th>회사코드</th>
		                    				<th>공장코드</th>
					                    	<th>주문번호</th>
					                    	<th>생산지시번호</th>
					                    	<th>라인코드</th>
					                    	<th>설비코드</th>
					                    	<th>작업시작일</th>
					                    	<th>시작작업조</th>
					                    	<th>작업종료일</th>
					                    	<th>종료작업조</th>
					                    	<th>상태</th>
					                    	<th>계획수량</th>
					                    	<th>삭제</th>
                                        </tr>
                                    </thead>
                                    <tfoot>
                                        <tr>
                                             <th>회사코드</th>
		                    				<th>공장코드</th>
					                    	<th>주문번호</th>
					                    	<th>생산지시번호</th>
					                    	<th>라인코드</th>
					                    	<th>설비코드</th>
					                    	<th>작업시작일</th>
					                    	<th>시작작업조</th>
					                    	<th>작업종료일</th>
					                    	<th>종료작업조</th>
					                    	<th>상태</th>
					                    	<th>계획수량</th>
					                    	<th>삭제</th>
                                        </tr>
                                    </tfoot>
                                    <tbody>
                                   	<c:if test="${productionPage.hasNoProductions() }">
					                    <tr>
					                    	<td>생산지시가 없습니다.</td>
					                    </tr>
					                    </c:if>
					                    
					                    <c:forEach var="production" items ="${productionPage.content }">
						                    <tr>
						                    	<td>${production.comp_cd }</td>
						                    	<td>${production.plant_cd }</td>
						                    	<td>${production.order_no }</td>
						                    	<!--클릭하면 modify 페이지로 -->
						                    	<td><a href="productionmodify.do?no=${production.wo_no }"><c:out value="${production.wo_no}"/></a></td>
						                    	<td>${production.line_cd }</td>
						                    	<td>${production.equip_cd }</td>
						                    	<td>${production.start_dt }</td>
						                    	<td>${production.start_shift }</td>
						                    	<td>${production.end_dt }</td>
						                    	<td>${production.end_shift }</td>
						                    	<td>${production.flag_end }</td>
						                    	<td>${production.plan_qty }</td>
						                    	<!-- 삭제 버튼-->
						                    	<td><a class = "btn btn-danger btn-sm" href="orderdelete.do?no=${order.order_no }" onclick="return confirm('주문번호${order.order_no}를 삭제하시겠습니까?');"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash-fill" viewBox="0 0 16 16">
  												<path d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1H2.5zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5zM8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5zm3 .5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 1 0z"/>
												</svg> 삭제</a>
												</td>
						                    </tr>
					                    </c:forEach>
                                    </tbody>
                                </table>
                     	</div>
                     </div>
                   </div>
                    
                    <!-- 컨테이너 영역 끝 -->
                    
                   <%--  <div class = "text-center">
                    	<ul class="pagination">
                    		
                    		<c:if test = "${orderPage.startPage > 5 }">
                    		<li class="page-item"><a href="orderlist.do?pageNo=${orderPage.startPage-5 }">이전</a></li>
                    		</c:if>
                    		
                    		
                    		<c:forEach var = "pNo" begin="${orderPage.startPage }" end = "${orderPage.endPage }">
                    		<li class="page-item"><a href="orderlist.do?pageNo=${pNo }">${pNo}</a></li>
                    		</c:forEach>
                    		
                    		<c:if test="${orderPage.endPage < orderPage.totalPages}">
                    		<li class="page-item"><a href="orderlist.do?pageNo=${orderPage.startPage+5 }">[다음]</a></li>
                    		</c:if>
                    	</ul>
                    </div> --%>
                </main>
                <footer class="py-4 bg-light mt-auto">
                    <div class="container-fluid px-4">
                        <div class="d-flex align-items-center justify-content-between small">
                            <div class="text-muted">Copyright &copy; Your Website 2021</div>
                            <div>
                                <a href="#">Privacy Policy</a>
                                &middot;
                                <a href="#">Terms &amp; Conditions</a>
                            </div>
                        </div>
                    </div>
                </footer>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="js/scripts.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
        <!-- <script src="assets/demo/chart-area-demo.js"></script>
        <script src="assets/demo/chart-bar-demo.js"></script> -->
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
        <script src="js/datatables-simple-demo.js"></script>
    </body>
</html>