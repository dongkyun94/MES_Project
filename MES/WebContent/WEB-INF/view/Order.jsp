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
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
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
                            <a class="nav-link" href="/MES">
                                <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                                Dashboard
                                
                            </a>
                            <div class="sb-sidenav-menu-heading">주 메뉴</div>
                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#basicDataLayouts" aria-expanded="false" aria-controls="collapseLayouts">
                                <div class="sb-nav-link-icon"><i class="fas fa-columns"></i></div>
                                기초데이터관리
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                            <div class="collapse" id="basicDataLayouts" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav">
                                    <a class="nav-link" href="#">공장관리</a>
                                    <a class="nav-link" href="linelist.do">라인관리</a>
                                    <a class="nav-link" href="#">설비관리</a>
                                    <a class="nav-link" href="#">품목관리</a>
                                </nav>
                            </div>
                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#orderLayouts" aria-expanded="false" aria-controls="collapseLayouts">
                                <div class="sb-nav-link-icon"><i class="fas fa-columns"></i></div>
                                주문관리
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                            <div class="collapse" id="orderLayouts" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav">
                                    <a class="nav-link" href="orderinsert.do">주문입력</a>
                                    <a class="nav-link" href="orderlist.do">주문조회</a>
                                </nav>
                            </div>
                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapsePages" aria-expanded="false" aria-controls="collapsePages">
                                <div class="sb-nav-link-icon"><i class="fas fa-book-open"></i></div>
                                Pages
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                            <div class="collapse" id="collapsePages" aria-labelledby="headingTwo" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav accordion" id="sidenavAccordionPages">
                                    <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#pagesCollapseAuth" aria-expanded="false" aria-controls="pagesCollapseAuth">
                                        Authentication
                                        <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                                    </a>
                                    <div class="collapse" id="pagesCollapseAuth" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordionPages">
                                        <nav class="sb-sidenav-menu-nested nav">
                                            <a class="nav-link" href="login.do">Login</a>
                                            <a class="nav-link" href="join.do">Register</a>
                                            <a class="nav-link" href="changePwd.do">Forgot Password</a>
                                        </nav>
                                    </div>
                                    <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#pagesCollapseError" aria-expanded="false" aria-controls="pagesCollapseError">
                                        Error
                                        <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                                    </a>
                                    <div class="collapse" id="pagesCollapseError" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordionPages">
                                        <nav class="sb-sidenav-menu-nested nav">
                                            <a class="nav-link" href="401.html">401 Page</a>
                                            <a class="nav-link" href="404.html">404 Page</a>
                                            <a class="nav-link" href="500.html">500 Page</a>
                                        </nav>
                                    </div>
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
                        <h1 class="mt-4">주문관리</h1>
                        <c:if test="${! empty authUser }">
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active">${authUser.name }님, 안녕하세요</li>
                        </ol>
                        </c:if>
                        <c:if test="${ empty authUser }">
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active">주문관리</li>
                        </ol>
                        </c:if>
                        <hr />
                        <!-- 모달을 열기 위한 버튼 -->
                        <div class="row mb-2">
                        	<div class="col">
	                        	<button type="button" class="btn btn-primary btn-md float-end" data-bs-toggle="modal" data-bs-target="#myModal">
									입력
								</button>
							</div>
						</div>
						<!-- 모달 영역 -->
						<div class="modal fade" id="myModal" tabindex="-1" aria-labelledby="myModalLabel">
							<div class="modal-dialog modal-xl" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<h4 class="modal-title" id="myModalLabel">주문 입력</h4>
										<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
									</div>
									<div class="modal-body">
									<!-- 입력 폼-->
										<form action="orderinsert.do" method="post">
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
						                		<div class="col-12">
						                    		<label for="item_cd" class="form-label">아이템 코드</label>
						                        		<select class="form-control" id="item_cd" name="item_cd">
						                            		<option value="103">103</option>
						                            		<option value="104">104</option>
						                            		<option value="105">105</option>
						                        		</select>
						                		</div>
						                    	<div class="col-sm-6">
						                    		<label for="order_dt" class="form-label">주문일자</label>
					                        		<input class="form-control" type="date" id="order_dt" name="order_dt">
						                		</div>
						                		<div class="col-sm-6">
						                    		<label for=delivery_dt class="form-label">납기일자</label>
					                        		<input class="form-control" type="date" id="delivery_dt" name="delivery_dt">
						                		</div>
						                		<div class="col-sm-6">
						                    		<label for=order_qyt class="form-label">주문수량</label>
					                        		<input class="form-control" type="text" id="order_qyt" name="order_qyt">
						                		</div>
						                		<div class="col-sm-6">
						                    		<label for="order_status" class="form-label">주문상태</label>
					                        		<select class="form-control" id="order_status" name="order_status">
					                            		<option value="초과">초과</option>
					                            		<option value="납기">납기</option>
					                            		<option value="미납">미납</option>
					                        		</select>
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
										<button type="button" class="btn btn-primary">확인</button>
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
					                    	<th>주문일자</th>
					                    	<th>아이템코드</th>
					                    	<th>납기일</th>
					                    	<th>주문수량</th>
					                    	<th>주문상태</th>
                                        </tr>
                                    </thead>
                                    <tfoot>
                                        <tr>
                                            <th>회사코드</th>
		                    				<th>공장코드</th>
					                    	<th>주문번호</th>
					                    	<th>주문일자</th>
					                    	<th>아이템코드</th>
					                    	<th>납기일</th>
					                    	<th>주문수량</th>
					                    	<th>주문상태</th>
                                        </tr>
                                    </tfoot>
                                    <tbody>
                                   	<c:if test="${orderPage.hasNoOrders() }">
					                    <tr>
					                    	<td>입력된 주문이 없습니다.</td>
					                    </tr>
					                    </c:if>
					                    
					                    <c:forEach var="order" items ="${orderPage.content }">
						                    <tr>
						                    	<td>${order.comp_cd }</td>
						                    	<td>${order.plant_cd }</td>
						                    	<td><a href="orderdetail.do?no=${order.order_no}&pageNo=${orderPage.currentPage}"><c:out value="${order.order_no}"/></a></td>
						                    	<td>${order.order_dt }</td>
						                    	<td>${order.item_cd }</td>
						                    	<td>${order.delivery_dt }</td>
						                    	<td>${order.order_qty }</td>
						                    	<td>${order.order_status }</td>
						                    </tr>
					                    </c:forEach>
                                    </tbody>
                                </table>
                     	</div>
                     </div>
                    </div>
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
        <script src="assets/demo/chart-area-demo.js"></script>
        <script src="assets/demo/chart-bar-demo.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
        <script src="js/datatables-simple-demo.js"></script>
    </body>
</html>