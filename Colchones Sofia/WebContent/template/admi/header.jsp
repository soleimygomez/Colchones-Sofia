<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">

<!-- Fonts -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700">

<!-- Icons -->
<link rel="stylesheet"
	href="${url_project}resources/vendor/nucleo/css/nucleo.css"
	type="text/css">
	
<!-- Font Awasome -->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.13.0/css/all.css"/>

<!-- Primeflex CSS -->
<link rel="stylesheet"
	href="${url_project}resources/style/css/primeflex.css" type="text/css">
<!-- Argon CSS -->
<link rel="stylesheet"
	href="${url_project}resources/style/css/argon.css?v=1.2.0"
	type="text/css">

<!-- Pastley CSS -->
<link rel="stylesheet"
	href="${url_project}resources/style/css/pastley.css" type="text/css">

<!-- Title -->
<title>${title_project}</title>
</head>
<body>

	<!--           -->
	<!-- STRUCTURE -->
	<!--           -->
	<main class="pastley-page-structure">
		<!--     -->
		<!-- TOP -->
		<!--     -->
		<div class="pastley-page-structure-top">
			<!--     -->
			<!-- NAV -->
			<!--     -->
			<nav
				class="navbar navbar-top navbar-expand navbar-dark bg-primary border-bottom">
				<div class="container-fluid">
					<div class="collapse navbar-collapse" id="navbarSupportedContent">
						<ul class="navbar-nav align-items-center  ml-md-auto ">

							<!--                -->
							<!--  NOTIFICATIONS -->
							<!--                -->
							<li class="nav-item dropdown"><a class="nav-link" href="#"
								role="button" data-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false"> <i class="ni ni-bell-55"></i>
							</a>
								<div
									class="dropdown-menu dropdown-menu-xl  dropdown-menu-right  py-0 overflow-hidden">
									<!-- Dropdown header -->
									<div class="px-3 py-3">
										<h6 class="text-sm text-muted m-0">
											Tienes <strong class="text-primary">1</strong>
											notificaciones.
										</h6>
									</div>

									<!--                   -->
									<!-- ALL NOTIFICATIONS -->
									<!--                   -->
									<div class="list-group list-group-flush">
										<a href="#" class="list-group-item list-group-item-action">
											<div class="row align-items-center">
												<div class="col-auto">
													<!-- Avatar -->
													<img alt="Image placeholder"
														src="https://picsum.photos/id/1/200/300"
														class="avatar rounded-circle">
												</div>
												<div class="col ml--2">
													<div
														class="d-flex justify-content-between align-items-center">
														<div>
															<h4 class="mb-0 text-sm">John Snow</h4>
														</div>
														<div class="text-right text-muted">
															<small>2 hrs ago</small>
														</div>
													</div>
													<p class="text-sm mb-0">Let's meet at Starbucks at
														11:30. Wdyt?</p>
												</div>
											</div>
										</a>
									</div>
									<!-- View all -->
									<a href="#!"
										class="dropdown-item text-center text-primary font-weight-bold py-3">View
										all</a>
								</div></li>

						</ul>

						<!--       -->
						<!--  USER -->
						<!--       -->
						<ul class="navbar-nav align-items-center  ml-auto ml-md-0 ">
							<li class="nav-item dropdown"><a class="nav-link pr-0"
								href="#" role="button" data-toggle="dropdown"
								aria-haspopup="true" aria-expanded="false">
									<div class="media align-items-center">
										<span class="avatar avatar-sm rounded-circle"> <img
											src="${url_project}fotos?accion=foto-perfil&id-usuario=${usuario_logeado.id}">
										</span>
									</div>
							</a>
								<div class="dropdown-menu  dropdown-menu-right ">
									<div class="dropdown-header noti-title">
										<h6 class="text-overflow m-0">Bienvenido
											${usuario_logeado.persona.nombre}!</h6>
									</div>
									<a href="${url_project}pages/admi/profile.jsp"
										class="dropdown-item"> <i class="ni ni-single-02"></i> <span>Mi
											perfil</span>
									</a> <a href="#" class="dropdown-item"> <i
										class="ni ni-settings-gear-65"></i> <span>Configuración</span>
									</a> <a href="#" class="dropdown-item"> <i
										class="ni ni-calendar-grid-58"></i> <span>Actividad</span>
									</a>
									<div class="dropdown-divider"></div>
									<a href="#" class="dropdown-item"> <i
										class="ni ni-user-run"></i> <span>Cerrar Sesión</span>
									</a>
								</div></li>
						</ul>
					</div>
				</div>
			</nav>
		</div>

		<!--      -->
		<!-- LEFT -->
		<!--      -->
		<div class="pastley-page-structure-left pastley-page-user-navbar">
			<div class="page-wrapper chiller-theme toggled">
				<a id="show-sidebar" class="btn btn-sm btn-dark" href="#"> <i
					class="fas fa-bars"></i>
				</a>

				<!--        -->
				<!-- NAVBAR -->
				<!--        -->
				<nav id="sidebar" class="sidebar-wrapper">
					<div class="sidebar-content">
						<div class="sidebar-brand">
							<a href="${url_project}pages/admi/index.jsp">Pastley</a>
							<div id="close-sidebar">
								<i class="fas fa-times"></i>
							</div>
						</div>
						<div class="sidebar-header">
							<div class="user-pic">
								<a href="${url_project}pages/admi/profile.jsp"
									title="Ver perfil"> <img
									class="img-responsive img-rounded"
									src="${url_project}fotos?accion=foto-perfil&id-usuario=${usuario_logeado.id}"
									alt="User picture">
								</a>

							</div>
							<div class="user-info">
								<strong><span class="user-name">${usuario_logeado.persona.nombre}
								</span></strong> <span class="user-role">Administrator</span> <span
									class="user-status"> <i class="fa fa-circle"></i> <span>Online</span>
								</span>
							</div>
						</div>
						<div class="sidebar-menu">
							<ul>
								<li class="header-menu"><span>Mantenimiento</span></li>
								<li class="sidebar-dropdown"><a href="#"><i
										class="fas fa-user-friends"></i> <span>Cajero</span> <span
										class="badge badge-pill badge-success">Pro</span> </a>
									<div class="sidebar-submenu">
										<ul>
											<li><a href="#"><i class="far fa-chart-bar"></i>Estadisticas
											</a></li>
											<li><a href="#"><i class="fas fa-file"></i>Historial</a></li>
											<li><a href="#"><i class="fas fa-user-plus"></i>Registrar</a></li>
										</ul>
									</div></li>
								<li class="sidebar-dropdown"><a href="#"> <i
										class="fa fa-shopping-cart"></i> <span>Cliente</span> <span
										class="badge badge-pill badge-success">Pro</span>
								</a>
									<div class="sidebar-submenu">
										<ul>
											<li><a href="#">Products </a></li>
											<li><a href="#">Orders</a></li>
											<li><a href="#">Credit cart</a></li>
										</ul>
									</div></li>
								<li class="header-menu"><span>Compra</span></li>
								<li><a href="#"> <i class="fa fa-book"></i> <span>Estadistica</span>
										<span class="badge badge-pill badge-primary">Beta</span>
								</a></li>
								<li><a href="#"> <i class="fa fa-calendar"></i> <span>Historial</span>
								</a></li>
								<li><a href="#"> <i class="fas fa-cart-plus"></i><span>Registrar</span>
								</a></li>
							</ul>
						</div>
						<!-- sidebar-menu  -->
					</div>

					<!--               -->
					<!-- NAVBAR FOOTER -->
					<!--               -->
					<div class="sidebar-footer">
						<a href="#"> <i class="fa fa-bell"></i> <span
							class="badge badge-pill badge-warning notification">3</span>
						</a> <a href="#"> <i class="fa fa-envelope"></i> <span
							class="badge badge-pill badge-success notification">7</span>
						</a> <a href="#"> <i class="fa fa-cog"></i> <span
							class="badge-sonar"></span>
						</a> <a href="#"> <i class="fa fa-power-off"></i>
						</a>
					</div>
				</nav>
			</div>
		</div>