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
                        <h1 class="mt-4">Dashboard</h1>
                        <c:if test="${! empty authUser }">
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active">${authUser.name }님, 안녕하세요</li>
                        </ol>
                        </c:if>
                        <c:if test="${ empty authUser }">
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active">Dashboard</li>
                        </ol>
                        </c:if>
                        <div class="row">
                            <div class="col-xl-3 col-md-6">
                                <div class="card bg-primary text-white mb-4">
                                    <div class="card-body">Primary Card</div>
                                    <div class="card-footer d-flex align-items-center justify-content-between">
                                        <a class="small text-white stretched-link" href="#">View Details</a>
                                        <div class="small text-white"><i class="fas fa-angle-right"></i></div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-3 col-md-6">
                                <div class="card bg-warning text-white mb-4">
                                    <div class="card-body">Warning Card</div>
                                    <div class="card-footer d-flex align-items-center justify-content-between">
                                        <a class="small text-white stretched-link" href="#">View Details</a>
                                        <div class="small text-white"><i class="fas fa-angle-right"></i></div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-3 col-md-6">
                                <div class="card bg-success text-white mb-4">
                                    <div class="card-body">Success Card</div>
                                    <div class="card-footer d-flex align-items-center justify-content-between">
                                        <a class="small text-white stretched-link" href="#">View Details</a>
                                        <div class="small text-white"><i class="fas fa-angle-right"></i></div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-3 col-md-6">
                                <div class="card bg-danger text-white mb-4">
                                    <div class="card-body">Danger Card</div>
                                    <div class="card-footer d-flex align-items-center justify-content-between">
                                        <a class="small text-white stretched-link" href="#">View Details</a>
                                        <div class="small text-white"><i class="fas fa-angle-right"></i></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xl-6">
                                <div class="card mb-4">
                                    <div class="card-header">
                                        <i class="fas fa-chart-area me-1"></i>
                                        Area Chart Example
                                    </div>
                                    <div class="card-body"><canvas id="myAreaChart" width="100%" height="40"></canvas></div>
                                </div>
                            </div>
                            <div class="col-xl-6">
                                <div class="card mb-4">
                                    <div class="card-header">
                                        <i class="fas fa-chart-bar me-1"></i>
                                        Bar Chart Example
                                    </div>
                                    <div class="card-body"><canvas id="myBarChart" width="100%" height="40"></canvas></div>
                                </div>
                            </div>
                        </div>
                        <div class="container" align="center">
							<div class="jumbotron">
								<h3>게시판 글목록 보기</h3>
								<c:if test="${authUser.id eq 'admin'}">
						   	     	<div>
						   	     		<a href="boardinsert.do" class="btn btn-success">글쓰기</a>	
						   	     	</div>
						        </c:if>
								<c:choose>
									<c:when test="${boardlist.hasNoBoards()}">
									<h5>
										<p class="bg-danger text-white">등록된 게시글이 존재하지 않습니다</p>
									</h5>
									</c:when>
									<c:otherwise>
										<!--게시판 시작-->
										<table class="table table-hover">
											<tr class="table-dark ">
												<th>번호</th>
												<th>제목</th>
												<th>작성자</th>
												<th>날짜</th>
												<th>첨부파일</th>
												<th>조회수</th>
											</tr>
											<c:forEach var="board" items="${boardlist.content}">
												<tr>
													<!--번호-->
													<td>${board.boardNum}</td>
													<!--제목-->
													<td>
														<a href="boarddetail.do?boardnum=${board.boardNum}&pageno=${boardlist.page}">
														${board.boardTitle}
														</a>
													</td>
													<!--작성자-->
													<td>${board.memberid}</td>
													<!--날짜-->
													<td>${board.boardDate}</td>
													<!--파일-->
													<td>
														<c:if test="${!empty board.getBoardFile()}">
															<a href="boarddownload.do?fn=${board.boardFile}"><i class="fas fa-file-download"></i></a>
														</c:if>
													</td>
													<!--조회수 -->
													<td><span class="badge badge-success ml-sm-3">${board.boardReadCount}</span></td>
												</tr>
											</c:forEach>
										</table>
										<!--게시판 끝-->
										<!--페이지네이션 시작-->
									    <div class="container">
											<ul class="pagination justify-content-center">	
												<c:if test="${boardlist.startPage!=1}">
													<li class="page-item"><a href="main.do?pageno=1" class="page-link">[시작]</a></li>
													<li class="page-item"><a href="main.do?pageno=${boardlist.startPage-10}" class="page-link">[10페이지 전]</a></li>				
												</c:if>
												<c:forEach var="pageNum" begin="${boardlist.startPage}" end="${boardlist.endPage}">
													<li class="page-item"><a class="page-link" href="main.do?pageno=${pageNum}">${pageNum}</a></li>			
												</c:forEach>
												<c:if test="${boardlist.endPage < boardlist.totalPage}">
													<li class="page-item"><a href="main.do?pageno=${boardlist.endPage+1}"  class="page-link">[10페이지 후]</a></li>
													<li class="page-item"><a href="main.do?pageno=${boardlist.totalPage}"  class="page-link">[끝]</a></li>			
												</c:if> 
											</ul>
										</div> 
										<!--페이지네이션 끝-->
									</c:otherwise>
								</c:choose>
							</div>
						</div>
                    </div>
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