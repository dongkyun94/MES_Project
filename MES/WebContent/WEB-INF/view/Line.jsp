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
                            <div class="sb-sidenav-menu-heading">??? ??????</div>
                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#basicDataLayouts" aria-expanded="false" aria-controls="collapseLayouts">
                                <div class="sb-nav-link-icon"><i class="fas fa-table"></i></div>
                                ?????????????????????
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                            <div class="collapse" id="basicDataLayouts" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav">
                                    <a class="nav-link" href="factorylist.do">????????????</a>
                                    <a class="nav-link" href="linelist.do">????????????</a>
                                    <a class="nav-link" href="#">????????????</a>
                                    <a class="nav-link" href="itemlist.do">????????????</a>
                                </nav>
                            </div>
                            <a class="nav-link collapsed" href="orderlist.do" data-bs-toggle="collapse" data-bs-target="#orderLayouts" aria-expanded="false" aria-controls="collapseLayouts">
                                <div class="sb-nav-link-icon"><i class="fas fa-industry"></i></div>
                                ????????????
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                            <div class="collapse" id="orderLayouts" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav">
                                    <a class="nav-link" href="orderlist.do">??????</a>
                                </nav>
                            </div> 
                            <div class="collapse" id="orderLayouts" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav">
                                    <a class="nav-link" href="productionlist.do">????????????</a>
                                </nav>
                            </div> 
                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#equipLayouts" aria-expanded="false" aria-controls="collapseLayouts">
                                <div class="sb-nav-link-icon"><i class="fas fa-bell"></i></div>
                                ???????????? ??????
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                            <div class="collapse" id="equipLayouts" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav">
                                    <a class="nav-link" href="eqmasterlist.do">????????????????????????</a>
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
                        <h1 class="mt-4">????????????</h1>
                        <c:if test="${! empty authUser }">
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active">${authUser.name }???, ???????????????</li>
                        </ol>
                        </c:if>
                        <c:if test="${ empty authUser }">
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active">????????????</li>
                        </ol>
                        </c:if>
                        <hr />
                        <!-- ????????? ?????? ?????? ?????? -->
                        <div class="row mb-2">
                        	<div class="col">
	                        	<button type="button" class="btn btn-primary btn-md float-end" data-bs-toggle="modal" data-bs-target="#myModal">
									??????
								</button>
							</div>
						</div>
						<!-- ?????? ?????? -->
						<div class="modal fade" id="myModal" tabindex="-1" aria-labelledby="myModalLabel">
							<div class="modal-dialog modal-xl" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<h4 class="modal-title" id="myModalLabel">?????? ??????</h4>
										<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
									</div>
									<div class="modal-body">
									<!-- ?????? ??? -->
										<form action="lineinsert.do" method="post">
					                    	<div class="row g-3">
					                    		<div class="col-sm-12">
						                    		<label for="comp_cd" class="form-label">????????????</label>
						                        	<select class="form-control" id="comp_cd" name="comp_cd">
						                            	<option value="1">1</option>
						                            	<option value="2">2</option>
						                        	</select>
						                    	</div>
					                		<div class="col-sm-12">
					                    		<label for="plant_cd" class="form-label">????????????</label>
					                        	<select class="form-control"	 id="plant_cd" name="plant_cd">
					                            	<option value="1000">1000</option>
					                            	<option value="1001">1001</option>
					                        	</select>
					                		</div>
					                		<div class="col-sm-12">
					                    		<label for="line_cd" class="form-label">?????? ??????</label>
					                        	<input class="form-control" type="text" id="line_cd" name="line_cd">
					                		</div>
					                    	<div class="col-sm-12">
					                    		<label for="line_nm" class="form-label">?????????</label>
				                        		<input class="form-control" type="text" id="line_nm" name="line_nm">
					                		</div>
					                		<div class="col-sm-12">
					                    		<label for="use_yn" class="form-label">????????????</label>
				                        		<select class="form-control" id="use_yn" name="use_yn">
				                            		<option value="Y" selected="selected">??????</option>
				                            		<option value="N">?????????</option>
				                        		</select>
					                		</div>
					                		<div class="col-12">
						                    	<label for="remark" class="form-label">??????(????????????)</label>
						                    		<div id="provision">
						                        		<textarea class="form-control" rows="8" style="resize:none" name ="remark"></textarea>
						                    		</div>
						                	</div>
					                		<div class ="text-center">
					                			<input type="submit" class = "btn btn-primary" value="??????">
					                		</div>
					                	</div>
					                  </form>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-default" data-bs-dismiss="modal">??????</button>
									</div>
								</div>
							</div>
						</div>
                     <!-- ????????? ????????? ?????? -->
                        <div class="card mb-4">
                            <div class="card-header">
                                <i class="fas fa-table me-1"></i>
                                ????????????
                            </div>
	                    	<div class="card-body">
	                                <table id="datatablesSimple">
	                                    <thead>
	                                        <tr>
	                                            <th>????????????</th>
			                    				<th>????????????</th>
						                    	<th>????????????</th>
						                    	<th>?????????</th>
						                    	<th>????????????</th>
						                    	<th>?????????</th>
						                    	<th>?????????</th>
						                    	<th>??????</th>
	                                        </tr>
	                                    </thead>
	                                    <tfoot>
	                                        <tr>
	                                            <th>????????????</th>
			                    				<th>????????????</th>
						                    	<th>????????????</th>
						                    	<th>?????????</th>
						                    	<th>????????????</th>
						                    	<th>?????????</th>
						                    	<th>?????????</th>
						                    	<th>??????</th>
	                                        </tr>
	                                    </tfoot>
	                                    <tbody>
		                                   	<c:if test="${linePage.hasNoLines() }">
							                    <tr>
							                    	<td>????????? ????????? ????????????.</td>
							                    </tr>
						                    </c:if>
						                    <c:forEach var="line" items ="${linePage.content }">
							                    <tr>
							                    	<td>${line.comp_cd }</td>
							                    	<td>${line.plant_cd }</td>
							                    	<td><a href="linemodify.do?no=${line.line_cd }"><c:out value="${line.line_cd}"/></a></td>
							                    	<td>${line.line_nm }</td>
							                    	<td>${line.use_yn }</td>
							                    	<td>${line.in_date }</td>
							                    	<c:if test="${line.up_date != null}"><td>${line.up_date }</td></c:if>
							                    	<c:if test="${line.up_date == null}"><td> </td></c:if>
							                    	<td><a class = "btn btn-danger btn-sm" href="linedelete.do?no=${line.line_cd }" onclick="return confirm('???????????? ${line.line_cd}??? ?????????????????????????');"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash-fill" viewBox="0 0 16 16">
  												<path d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1H2.5zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5zM8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5zm3 .5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 1 0z"/>
												</svg> ??????</a>
												</td>
							                    </tr>
						                    </c:forEach>
	                                    </tbody>
	                                </table>
	                     	</div>
                     	</div>
                   <%--  <div class = "text-center">
                    	<ul class="pagination">
                    		
                    		<c:if test = "${orderPage.startPage > 5 }">
                    		<li class="page-item"><a href="orderlist.do?pageNo=${orderPage.startPage-5 }">??????</a></li>
                    		</c:if>
                    		
                    		
                    		<c:forEach var = "pNo" begin="${orderPage.startPage }" end = "${orderPage.endPage }">
                    		<li class="page-item"><a href="orderlist.do?pageNo=${pNo }">${pNo}</a></li>
                    		</c:forEach>
                    		
                    		<c:if test="${orderPage.endPage < orderPage.totalPages}">
                    		<li class="page-item"><a href="orderlist.do?pageNo=${orderPage.startPage+5 }">[??????]</a></li>
                    		</c:if>
                    	</ul>
                    </div> --%>
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