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
                            <a class="nav-link" href="/MES">
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
                        <h1 class="mt-4">품목관리</h1>
                        <c:if test="${! empty authUser }">
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active">${authUser.name }님, 안녕하세요</li>
                        </ol>
                        </c:if>
                        <c:if test="${ empty authUser }">
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active">품목관리</li>
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
										<h4 class="modal-title" id="myModalLabel">품목 입력</h4>
										<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
									</div>
									<div class="modal-body">
									<!-- 입력 폼-->
										<form action="iteminsert.do" method="post">
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
						                		<div class="col-sm-6">
						                    		<label for="acct_id" class="form-label">구분</label>
						                        		<select class="form-control" id="acct_id" name="acct_id">
						                            		<option value="제품">제품</option>
						                            		<option value="원재료">원재료</option>
						                        		</select>
						                		</div>
						                    	<div class="col-sm-6">
						                    		<label for="item_cd" class="form-label">품목코드</label>
					                        		<input class="form-control" type="text" id="item_cd" name="item_cd">
						                		</div>
						                		<div class="col-12">
						                    		<label for="item_nm" class="form-label">품목명</label>
					                        		<input class="form-control" type="text" id="item_nm" name="item_nm">
						                		</div>
						                		<div class="col-sm-6">
						                    		<label for="item_spec" class="form-label">품목규격</label>
					                        		<input class="form-control" type="text" id="item_spec" name="item_spec">
						                		</div>
						                		<div class="col-sm-6">
						                    		<label for="item_spec2" class="form-label">품목규격2</label>
					                        		<input class="form-control" type="text" id="item_spec2" name="item_spec2">
						                		</div>
						                		<div class="col-sm-6">
						                    		<label for="item_color" class="form-label">색상</label>
					                        		<input class="form-control" type="text" id="item_color" name="item_color">
						                		</div>
						                		<div class="col-sm-6">
						                    		<label for="cust_cd" class="form-label">거래처</label>
					                        		<input class="form-control" type="text" id="cust_cd" name="cust_cd">
						                		</div>
						                		<div class="col-sm-6">
						                    		<label for="acct_price" class="form-label">단가</label>
					                        		<input class="form-control" type="text" id="acct_price" name="acct_price">
						                		</div>
						                		<div class="col-sm-6">
						                    		<label for="currency" class="form-label">환율</label>
					                        		<input class="form-control" type="text" id="currency" name="currency">
						                		</div>
						                		<div class="col-sm-6">
						                    		<label for="unit_cd" class="form-label">단위</label>
					                        		<input class="form-control" type="text" id="unit_cd" name="unit_cd">
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
                                품목목록
                            </div>
                            
                    	<div class="card-body">
                                <table id="datatablesSimple">
                                    <thead>
                                        <tr>
                                            <th>회사코드</th>
		                    				<th>공장코드</th>
					                    	<th>구분</th>
					                    	<th>품목코드</th>
					                    	<th>품목명</th>
					                    	<th>품목규격</th>
					                    	<th>품목규격2</th>
					                    	<th>거래처</th>
					                    	<th>색상</th>
					                    	<th>단가</th>
					                    	<th>환율</th>
					                    	<th>단위</th>
					                    	<th>등록자</th>
					                    	<th>등록일</th>
					                    	<th>삭제</th>
                                        </tr>
                                    </thead>
                                    <tfoot>
                                        <tr>
                                            <th>회사코드</th>
		                    				<th>공장코드</th>
					                    	<th>구분</th>
					                    	<th>품목코드</th>
					                    	<th>품목명</th>
					                    	<th>품목규격</th>
					                    	<th>품목규격2</th>
					                    	<th>거래처</th>
					                    	<th>색상</th>
					                    	<th>단가</th>
					                    	<th>환율</th>
					                    	<th>단위</th>
					                    	<th>등록자</th>
					                    	<th>등록일</th>
					                    	<th>삭제</th>
                                        </tr>
                                    </tfoot>
                                    <tbody>
                                   	<c:if test="${itemPage.hasNoItems() }">
					                    <tr>
					                    	<td>입력된 품목이 없습니다.</td>
					                    </tr>
					                    </c:if>
					                    
					                    <c:forEach var="item" items ="${itemPage.content }">
						                    <tr>
						                    	<td>${item.comp_cd }</td>
						                    	<td>${item.plant_cd }</td>
						                    	<td>${item.acct_id }</td>
						                    	<td><a href="itemmodify.do?no=${item.item.item_cd}"><c:out value="${item.item_cd}"/></a></td>
						                    	<td>${item.item_nm }</td>
						                    	<td>${item.item_spec }</td>
						                    	<td>${item.item_spec2 }</td>
						                    	<td>${item.cust_cd }</td>
						                    	<td>${item.item_color }</td>
						                    	<td>${item.acct_price }</td>
						                    	<td>${item.currency }</td>
						                    	<td>${item.unit_cd }</td>
						                    	<td>${item.in_usr_id }</td>
						                    	<td>${item.in_date }</td>
						                    	<td><a class = "btn btn-danger btn-sm" href="itemdelete.do?no=${item.item_cd }"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash-fill" viewBox="0 0 16 16">
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