<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

</head>
<body>
	<div data-ng-app="SeekersApp" data-ng-controller="businessPageCtrl">
		<div class="container-fluid">
			<div class="alert alert-success" ng-show="showSuccessAlert">
				<button type="button" class="close"
					data-ng-click="switchBool('showSuccessAlert')">×</button>
				<strong>Done!</strong> {{successTextAlert}}
			</div>

			<div class="alert alert-danger" ng-show="showErrorAlert">
				<button type="button" class="close"
					data-ng-click="switchBool('showErrorAlert')">×</button>
				<strong>Error!</strong> {{errorTextAlert}}
			</div>


			<nav class="navbar" role="navigation" style="background: #6b5b95">
				<!-- <div style="background: linear-gradient(to left, rgb(142, 158, 171), rgb(238, 242, 243));"> -->
				<div class="panel-heading" style="color: whitesmoke">
					<a href="#/login" class="btn btn-xs btn-primary pull-right"
						style="margin: 15px; margin-top: -1px; background-color: violet; padding: 7px 7px; font-size: 17px; line-height: 1.5; border-radius: 6px"><span
						class="glyphicon glyphicon-log-out"></span> Logout</a> <a
						href="#/pic/upload" class="btn btn-xs btn-primary pull-right"
						style="margin: 15px; margin-top: -1px; background-color: violet; padding: 7px 7px; font-size: 17px; line-height: 1.5; border-radius: 6px"><span
						class="glyphicon glyphicon-backward"></span> Back to Upload Page</a>
					<h2 class="fontfamily" align="center">
						<strong>WELCOME TO YOUR BLOG</strong>
					</h2>
				</div>
			</nav>

			<div ng-show="isDataPresent" style="background: #efeff0">
				<div class="container">

					<div class="overlay">
						<table id="listTable"
							<div class=" row">
								<div class="col-md-8 row">
									<div class="col-md"
										ng-repeat="a in place['placeDetailsDTOList'] "
										style="background: white;">
										<div class='  '>
											<center>
												<img src="{{a['fileName']}}" height="400" width="700"
													alt="No image available" class="w3-border"></img>
											</center>
										</div>
										<div class='col-md-12' align="center"
											style="font-weight: bold; margin-right: 565px">
											<h2>{{a['placeName']}}</h2>
										</div>


										<div class='col-md-12' align="center"
											style="font-weight: bold; margin-right: 565px">
											<h3>Month Visited: {{a['monthVisited']}}</h3>
										</div>



										<div class='' align="center"
											style="text-align: left; padding: 41px;">{{a['placeDescription']}}</div>

										</div>
								</div>
								<div>


									<div class="w3-col l4 col-md-3">
										<!-- About Card -->
										<div class="w3-card w3-margin w3-margin-top">
											<img
												src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSydJsDQX5ZzLhgtE_7H7KDhktQpafkhpyz2x1jpW3_QZiRGtN0SQ"
												style="width: 150%">
											<div class="w3-container w3-white" style="width: 406px;">
												<h4>
													<b>Seekers</b>
												</h4>
												<p>I am a blogger and a photographer who loves to travel
													around the world. I love sharing my experiences through my
													blog. I am always ready to embrace new and unique
													experiences.</p>
											</div>
										</div>
										<hr>

										<!-- Posts -->
										<div class="w3-card w3-margin">
											<div class="w3-container w3-padding">
												<h4>Popular Posts</h4>
											</div>
											<ul class="w3-ul w3-hoverable w3-white">
												<li class="w3-padding-16"><img
													src="data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBxITEhUSExMWFRUVFRgVFhYWGBcZFxgYFxgWFxgVGBgYHSggGB0lGxgXITEiJSkrLi4wFx8zODMtNyktLisBCgoKDg0OGhAQGislHR0rLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLSstLS0tLS0tLS0tLS0tLS0tLf/AABEIAKoBKQMBIgACEQEDEQH/xAAcAAABBQEBAQAAAAAAAAAAAAAFAAECAwQGBwj/xAA/EAABAwIEAwUGBAUDAwUAAAABAAIRAyEEEjFBBVFhEyJxgZEGMqGxwfAUUtHhByNCYvFygpIWM6IVJFNzwv/EABgBAQEBAQEAAAAAAAAAAAAAAAABAgME/8QAIBEBAQEBAAMAAgMBAAAAAAAAAAERAhIhMQNBMmFxE//aAAwDAQACEQMRAD8A9rhOpQmhVCASTpKhJ0k8KBJ0yUqKdJJJAkkkkCSSSQJJJJAkkkkCSSSQJJJMUDJinSVRAqohWOCZBUaScUlJPKqEE8qISRUiVW5ykVWQiIlMVNMghKiSpOUCggUykQmhEFZSUU4UaPKSSSBJ1FOEDwlCdJRSSSSQJJJJAkkkkCSSSQJJJYuLcUo4aka1Z4YwbncnQAC5PQINqS47hX8SsBXqCkHPYXHK01G5WuJsBIJiesLsJQIpkkyoSSSYohimhIqJcgcqJKYvUSVUSlNmUCVGUFhKjKiHKSBJJ4UHlA5SyquUzqlkEi0JWWcvUe1TE0XDlIFYW1FY2qpi61p1VTqq3MFGiTpApIEkkkgSSSSBJJJIEkkkgSSSaUDryz+O1cdnhac3L6j8vMNDRPlm+JXqUrkP4mcJo18I7M0Gs3/suvmbLmZojYgXm1lYSvn2V9RcDxgrYejVFxUpMdfW7QV4Bwb2eP4in2uXsxVZnBIgtDgXAg30lfQuCdTLGillyNAa0MjKABAaANABsi1eSlKSYoyRKYlReVT2iC4qslVVsSGglxAA3KFYzj1Nvu989LR4ytSWs3qQXLlAvXMUeK1JzF030NhHJEm8UpluafLfwj66K3mxmdwULlAvQOpx4T7ttzI+HVZMTxsunKcg/wDI/p5Kzil7jp86sa5cPR4rUZo4gddPQolhfaQCz+91Fj6aFLxUncdOXqp71RRxLXtzNMg/cHkUi5YxvU8yTioBRfUQQqFU5k1Woqs61GaMOYQmBRF9IFZ34U7LOt4qa5WB6bsSE8ILGPWhrlkgJOqhokmFDW1JYRxCn+f4FTZxGmf6gPGyZV2NaSxDidPn8ClU4lTH9U9ACmU2NqSx0MW1+nodVcHphq5JQFRTUUxTKRUVRzftb7S/hctNsGo8F175RoDG5N/RecYni73vc97y4xcuPXTkB0Wz+LOIJxBhjpptb3o7uk2InnvC5XD4rMzvASQdANiInrK78ZjnZXQUfaEt5cwd7kxHh9wjfCfalzLi/Q/ULz2o7vdP2n6pMxRAJmIEiClwx7/wTirMTSFVngRyMA/ULcSvLvYHjJZh6jGm8sgkaCCCR6AI/h+JPpvLpk/1Amx8Vz8Dzx2BVLqSHs4/RylxOUgTlOp6A6GUPoe19Muh9NzRsR3vUQCFPGr5Rb7R1W5Q2dDLrjl3Z5LlqYJdIvPjebclfX4m6q9zsohx32gQPhHostbFubYuAk8o8F25lkxx6u1rOGaATMHxv4QsOJqOGrtNDCqxeLYZGYmN53+t0NqVibkytSI2uri9yTspVKjrB2gA/wAShtVxC005IAJ7x9fP4KmLG181pt1NpUTcw2JA56nnKVTBZHOzuBuQBNyYB28Qq3ZWixidTv6Jq40vxFSiZa5wkAyDE+PMIvwz2leSG1RYj34jncjSPRcy3ExpJ8fnbrdXNrDKQ54BjS++saxspZv1Y9AGJ3myqqYsLgDWfTsx7m6SAe7Jm/oAURo8XGj/AHtyNFjwXa6R+KVf4hC2Ypp0IPgp9unizr0mpUa0S4gDqQPmoMxTDcOB8wvNX4xzjLnEnmTKk3FLP/H+2/8Ar/T0etimASXgeaHVOMUxpLvgFxgxqb8Weas/Ezfy11dfjQghog80LfipQk11Ht1qcYxe7Rb8R1SFZCe3SOIVxNFTiFE4vqhX4kKDsQDpZMXR1mJ6x16ovhOMCIfMjcfVca2utZr9Vm8rOrHdUaocJBkK4PXGYHiLmXBkbjbz5I3heMNdqI+niud4rrz3BvMmc4ASbAak6BB+Jcdp0W7vcdGt+JnQBcfxXi1StUGYw0mGskw24Hmeqk4tW9yBPtjiO2xVamx1qj2UwRfZoJ8r+i4bDMcHOadWgjzkCPVdFg2k4kPzGM7t+QJnzKw42iTiqjgDEk3AGhBmNfvdX8cdfyX3n9BzbkdZ+AP6LPiHnLbwRWphjl7hGam7OORY64PrIVVPBhxa8e7nbIi4uJHJw6hdLHOUd9mqeS+cmWxlIAGxv1CNvxt4O9lz3CmRVcZMS5seEgFFgZJde0Hckbyen6LP4rsup+bmbLG97RlzE66GYnkY6BY6mJbeXG202PjdYsRinZiAY58jbWDz+qzOxEwNNPhpK6yOWNdWs5veb3QeSqqP6gzdUVsYS3LtPyWbt/hy+7qmN8GY5wmr1Gj3TmPwHksP4o+E8oHwURW5WKLjUXEySZJvz9eSdtRzSL/Qg625KAoEgOBuZvzO+1t/RJ9Se7dr5nLG+5n1RcXVqj9rWvrM/VZ6znEidY9FPGYiDezxGl2u8FkfiXxJ0PRSGNbaloHmoiqc3dAtqVgdVOxPoofiHDfQz6KrghUxJnmqKlUm9j81EVTHLf8AdKnUbfMBfpof0QxOniSL8twrv/UH/nKzFzTO0aRvPXfwVeQfmHoU0x1ziZNpGv2FHOD0nlpP0/ZU4rFEQAYHx8VSawJuY3RzxoNR2wSbVN+lyrfxAJloJuBygGBuqKjxJLQ6bg3gemvOyaYt7c6TomNUj9isneMiZGsT57pmU3OHcykmwBMXn79UTGvtlE10Or1ntqNpk0zLROVxkSBqJ6/BaACQXT0E7nlP3opOpfi3ixuLxAiTI+PIedlB7/JY6OIc0jKdDI5FXufI0IJ52Fjpy5qmL2v+/RW5oQxlaD0VnbD/AD+yJgtTxICtGJgZhsg4xCtw7c1zMdN9dP2UpiVfH5nlzw7TQcuVv31VQxjS4THU3RQUKb2ZTctAEwRMTAv0WHHcEgSx0zoCL+vqs+UbwH4XTLqtTm0OjoSTB/8AE+qm/hlVrh/OiJu1jBfrlgOE/cqhtejRrntX2gPdAcY17vdBm5Gy11faTCumIjxcD5ZmiPX6EY4zHX8m+StmEYSC50GTlc2SwT72nfpknYnLzvZbHcBbUAylhJmS0m40BGWQ/lpCrpcTw7iJObmWEOPQkMJ7391joEX4XXph3dqm9yTmk+LS0X2mJV1hzFfCGjXdSkmGh0nW7Y+BBROjiCwTlzNmCT0I38D8VTxPF0avEHUwSC2nkYYMF8B8Ea7kJcSLWDISGsi8mJfYEz6/FTi/W+/kZsaDIJLYPuwbATp5KunSm2YDYz98oVjadJzJDjI65gR4iwWN5blJBggj62+C6a54urC2hk6nmRYjxkH1WTXZWtougzIgbhxG24BhUmiTyv1Fj15K6qLioFynToOJDQLkwPkh/EKbm1HMLtI0JjRZ66xqTRfDcSfS9wwDqLaqzFcXzicsPBkOFvh5ITR931+ZTlWWX2Y0OxrzvvPn9EqOI1k6rMVGVTF9SoNgqS5RzJiVNXFwrnTl0uqy9QDoUZTTFhqnmlmVUppU1cdZXq5jbw5KOfffboma114afRWUaD9SLaT9VvXFLK43mZ3ka2TtpuaCZvpAI9Un4ZwmG7fZVTQ7SNtfWyBnBw0PyHqs3EmnsnnbKbq/I4nlA1Oh6WB+Kq4g4ihU07zYdfmRb5KdX0s+h/DeE1WVqJOUS3tJBnKHNdBdy5rpsLRs5piWuk8iCLCRpp81z3COMVX1aYflhjXCbiQGx3rxoI21XStpgyaZa1/ukNMtPiNhFly4+N/k+mbwuWgm0xy3/wArFVwxZY3kWNx9lbqfFiZa4QTqZtIHLb7KtbUbU/lmHE8rE7zyEfdlvb+3MBzrQwuMWuZva8D5rXX4BWDXPA7rLm4zZddBINuRVeBw4LZLgDIOviInTr6rXlDF+Gw7ZGYEtykkiLi1wJ6q8UmB4LajshO23ha3w1VdUvpauGUgNy6xY26anyUZY12UkZXD3uRM6xFv1lZGltY0nicrqbjqSWkEbd6x+9ED4Ni3dqDLpJeLuJtGnj+yKYxzHNbDtNW63M6CdJtbogGArN31uJtzB/VY6nuN8/KhxjhhmrIcXy52eAWuAZGWR7vO8Cea5Uhd7Xxl5dfS4ttF9oj5LhqggkciR6K+ORvnrVEK+m5wtJHgVYcMOx7Sb58sdI19VSxZkatdDw6i4Na8NbDJeCSZkwDYeIstPEana4eo8i7S02094AmOsqXAmOdTGSC6HiCJBGhHiQFgxbnBr2xE2I5Q4GOeoW7JlY3bBH2e4gYDCP6YFzc369fgtWLaHkCmQQSSAdbj3XEeCEUMWXU2kMDckNzTqRBEeqnhq9QEvZ7wk7eBMbqcz0nX10+DqZgQ9oYWiS3XncRf7OqF8XwTRDnWAMOIkek7+WyG1ppuljtdwfn0KsoY3Rrs7mTOUuBuYm55wVcRrbwUODSx/vA5RMkkagAa25FAcdSIf3jf10C3Mpy7IwuvHLneL2g7hUcSwhpOAJmRM+Nlnr43z9Rc2Gj72F5VRWrD55DwC+GxGsAfIfumxOGe4l2TKBGtvs+CvN9F+s7XNIM2NoA35/qq6jYOsjmrRhXEZgBHiPlKZlMjaTpEW8ZnVa1FJtY2UZWungajoGRxPOD/AIhV/hHSBB72hIyj1No6ygoKlSe0TmaTYxBiDFjpeCiLeDd2XVWN6Q53xFj5Kh3DHEgMOe07Njp3jr0U1WEpk2KD2GC0jx09Vn/EeCz5RcewPpVb6dIB+pUKGNAcGup1Nr5Hxp+YSFfSeSLzA/KDFre7cmdlc3PFoFrTNvItWXFexk+6Y9fqVMMI68xMW9CfisYofnyuEzeLcrBsHzVlJpFs7BygGwHQmFASY4Afv+iDe22Jy4Gs4R/QBmAgg1Ggy03+CJUZ07Rx5CBB8YF/EQgf8QzGBfYiXU5H9PvtOsdFG59B/wCHnEKTq4pNY/PkqPLjEASIa2DMRGsXlelD/T5kiPnK8m/hbRzYtxESKLvTMwRrpcei9VNMTGUGdbA+qy3frQGjkPgg3tTVDKQADZfUaBNtAXwDePd5bIq0ATHnETPWLrm/a2vT7Six9IVLOeMxs02g6+KInhKzg1veB7zc0CWxN2g3+K6jsqf5W+gXFsrU5zNmYMMzd2Bqcs2iNYRP2k9oxhMI2tlzPcGtpi0FzhNzvADnW5dUpHQVKNMasB/2hV1KVJolzWAdQAB4mENwntDTOHFd+YDI15hsuAduWtmEAbx+tUNTsnDNUcOxnQBozOa5uvuQecuRRH2r47SwtNj206VTM8t22E7DwXKfw5dSfVu0SWvPeDSIzCBJGyIcZpDH06bKzy11Jzu/TAh5LZsCTEC8dD4rH7CvpUntlkHIWF7QXAGXElxjuTlAtM90pp+nePwtL/42f8WryXivshiH4usGBoYar3B0w0BxzRpNpi3Jd7gfaCoXOFVhYA5+VzRmOXMckgTq2NkzMQ17nxo6/wB8kvV5no5ntyNfgZZwvEU8ofUp4gEOa2Sb0bC06ONlyWH4JiTBGHqwTAORwEkwLkQvR+CVc+HxLHiHPqVIzC4lrQ11/AQei24CsOwE9HfGdfVPPGpPqj2P4a+lhg2rTDXhztcpMEyLgnmfRV+2YY3CVH5AT3LgCZztGsIvSxzRzb1cCBygEi5QT24qh+CqBkOJLIAnNaownu+q3a4z6q9ja9OrQ9z3HBhLg27gxpJEbX8UUrtYNGA21AB/wgnsbiqX4cBrbyA8tH9eVskiCT46IzUrkE2nrYA+WqsZ6vsnUGxOQX6AeSy16OXVoHl8FZUquGgBboRlzGI0FvuVTiKDyDeLGQQZjnD/AKKss4IvIhc77TjvM8CPQo9Tpnm3w75d5ho/dA/auAKWmrgYJI22IkeaVvj+TRwQgUz4j1ICI5eg9QhPA6gDX3Gxv59Dt8ltfiYaDYidvrKvPw7/AJNOXnZQ8DPhCpdUn3ZuLyDJnkQANtlNhIFz4TNt4MqsnJNpClmPP4rO7GMMCHG3P5m/zUu21hthuf3RVgceaRFrrOMTPIW5mfv1T9pN5geY8rhAsreQHkFHsm/lHoFE1x9xdR/EjkfT9kV11CmxsuzCN8xnURqT00KmzF0z7pbPMG8W6LCcwb3ml5nXK0egLvl6K1tIu0AbJ/Jf1LYPlKwy1HiVPTtGk6ESM3otAeyxFgY/uB30g6oWadUPDWhrjscgFzsYEt9Fsp1Hua3M1rajbOkE26EFp5a+m4K3UqzQIDBI5d2fAclzn8RMXOBgOaQarAQDOkkDpojzK9MVAxzmBwuACWnpIzEELmv4n1gcK0NmDXbvIPcq3F/ost8/Qv8Ahe4DE1HzE0S0ASSSXsNo8F6dXrANmY5lwcB58vNeYfwspk1KxlwhgiJgnNeY2svSKeK7pBy5g4AwQNwLcvNZa6+tdCoYJeBm2gyCI10JaNdtlw3ttjM1dgMNy0r5XSLknXKLRB0XS1uJ0mFz3VHaaB23diAZN7nyXE+1VUYit2zHODMjGlrgJJzG/wAQPJZ8oRd7OCnVJDRAAJ3BIiAYNjaLjzS9tqdTEUqTGR/7dhcW3uC0DU6uGUiBrJvsquDOa14LQXOAIGXTnBFoMXuQtWDxLg92Yw0PIIi9nXmDv708uhIVl1f2HeyntI2mWsfU0bcWDIsMt99ZiAiGPxYik6gWue3ECGssXtc0mTaBbKD9hYKHBqVd747JozvylkmWyQ3ukZdLx4c10/s5wWlhA5wc4udYvdFgLwI0/dNUOpcJpNDiWsdWBLgxj25GEhrcgBcCHFsfdlkZxDHMdejlBhtmOI1/M1xHRdNxDGS2z2tpwRnMEjacp946wIjnso0XFjA1lR1SBEgtEwNwSG35jmphrLQ4gRT/AJlKo0RL3ZIYANTIeHRreYv5oRwziYLidp1vME21RPEYx4MZOhDn0iG83DL3r2EaeK5ysA3E1QD3S7MP919TreQnU9NcfRXEcXytLWtfmyug96IBIkAWHj0KopY+KQA/LCt4XVDaTu0ADXWDjUbBBJBJaGHIP9RQPh4JY1s3MN9TClnprn7XXUX035BULhMNDTUeJ8Wg5XTYwZlEadF+eQWFp1DpDoFtrHzXO/i8QMzMjXXu8OfG2xBjw2lWNrV+8XOYGxDQHc5vDssmed1txG6uGYS7Kym3vnMWOkkkA97uWdAHpqsLqlIszBzgQRJ1bsINxH7rluI1aoJazEVH2IIZTcRDtbgAGRv0VWEZXpgFmaoHWjs6toAtm00A02Ho8jwlHquOyw0xJNoFx8Z5hQGIDjctPKHvY4eR25qih7O46u4VCxrWEayAT1yi/rCnxbhtegbjO0AHuyQBpePdvztrfVbnWsXjEMZSfmkRb3ZDXW9SZ6wub4+IDT3R3oMTMxuCiVTGHnAgWLB8xCD8bqk026+/odNDpK1b6OJ7bODy6YAMATmdA39UQJDDLg14MwJEDpAaJCEcCcSDDmiGyZMTB2Ma36LbTxpBs8HeC1s/8gTKc/F7ntvdjNIhvQFxGlhAgKAru3d6iJ8iblZalc8wJ0ufkbKIxpB2vuD+6rOLqla8iT5N+UKsuPU+Nr+QWatiXTMCD0j15pjVcWzkEHkQ363VXBE1SRdseF/m1Vuc3n8kOFYa9/lrdQD2nfLf+rvH5fVRcEalZuzr9bn1Chn/ALvn+iy5BPdIcdzGnoPqrezdz+CDuKNSBBc4nm4G+t5DQB5clpqOEXAMbwCD4SFy1bjTBLDUc+4sCGC/MwHQOSfiXtG0VaIpgCHAuIze6bQZsbTNlz8ozOa6U8QyHKSJN4zQI5ibfHqtNQuAkkmdLk6+pXP47jdFziCA8iTYMgc40v8AG6r/AOoKVNoLu0bs1sRJ/ti0/sqZRzEY+AC8xJIGpO5iG39EJ4hR/Gsy5CGU35xIPe7rhqSSB3jqOSC4XiJq131cuU02xTAl0l8tBcLSdeQ2RHhODe85n1XDMQMrSQLNMA3vt1Uvtuc4EFzsCHuDm3AyNJEuJMgkNINhebajnavCcZrvZDiS57hBa78pBJczNG9rD9NH8QMH2bKF83vNn/SG6nwIt0vKBcFxdNgeHNcS5sAss5tx3p+x0WPF0gxinOaH06hJdEw2S2DuPKFQD3LtIaJgiSTaNwNt7oc3ibnVJcRItJLtJEgGZEp6lZ12gh06Cfd8eenVc7x7TGrgWMNJ2ZwL82oDo+sFTrgPrOJcYc4uyNcIgmYk8j9hZsFwas/K0Zb7yY5bbnkEWw3BDEdi3MZh2cxGuaL/AHHntUsbhmQDSa45NWipTIvvmzEkxNhsAimG4qabXOqUHBvdABe4w7Ul0GI3B/WVk4RwtrXnPleWkyw5bAWk5WEEWMC23guirlmVw7KW6SIsQTa4A35+XOyJWPDk4imSGBoJALqQeTaO6STr5qxr8vc7Wo8iA7K14Pm4kD1PNZKnZNdApPaB72VwaI6hk/ZWTA18ri4VC2lezs+U6btyy7rKqNHE+HF4k55AinAcY9akeg38kIxIeKlLNmzf9t2ZoadbWmTqbkDXdH6WPEE/yyOjQbm8Rm+uyFcb9ocrmsdTaSCDGXI7Ygh15gjTwSxeb7EcDjHimzK2oQLOByZSAdjIIM3vP1QjAtAc0lstOZ0EuAvmDQct9dgdlo4XxOkaLqppsnvWMGPygGJlDeDe0rZZSfRa8HQDMXE7GxtFzbmUxqft0vCn0Pd7JpEa9jO9my6XO/ZFRQoZgabWh8atY426ESBoNQdFnwOMmGmoWkR77DbSwc4yWgWvfvSr8rc2btY0tTeG5omCfzG+9/RHNtnM0tdTqFsaFlKD0gi/osGJxNLDtDix9IaAS0crANdIW7talsvaHmHMAPkcom3VT4pgaVZkVxmDbw4OsekQdht0VFHB+OUqhLabmnc/zL6C8SeafilCoW5m19DILxmidg5lwJIiQYhYaPs7g2tljRqfedmG40eL+fJbHVcgAaKbmAaBoLxHJhPeGmmin+n+BH/SVat36jqTDqDTkgmbktBDfQrmvbL2crUGZi3OyQc7cxA1Het3ddyvTcPiKZAyOc3UgWaOZ7sR6pVn2M1zB/8ArM6iPdMpPRrxPgTyHmI9w66ahFX4pps5oExcDX0hdtjPYnDOOem7s6km7QzIZ2NMCI6thcnxrC1qDi17ABMBwPdd4dYGhutyl91QygwizgJ5x9DKkaNoOR3iA3zmChz6jd4HQZQfgCp0shsco66/EmFpMbadK+lP/SAfrr6KL2cgW+Yj0uszjTG7D4a+o/RO+sxwjO4DxKGLgHgT3PT6qrPN8w8svylZQ9ulzve31V4rZrFhPpCGLJMaz45VXmfyHr+6qJvYR8VGT+Uf8Ci42cV4e/DPArZXOcLQ6wEkAkZQdbg/4Q5wkkuJ5jkT18iRuoY7EPe9xe5zjcS4kmNYk+JVYcY12XnjTocJi2tpvc5rjOj22LY/221AlBsRis5LnudmFgCZMTcAm4MJ8MJLpvprfdYqwuPP6LXNJGzheOLXkuEtdGeNbaX2RGhxej2pNRr3Mmzc5LcsHK0gkzBI1J35IJQPdKhU98+JWmnSe1fHW4mm1oZHZ1CBeZblMwRtPzCBUcPUtlpu5XGvwW7gw949AiZNx4/qifAEcIq7sdP2URwPB6gILsjfGSfH7K6R1Nt7D06lYeJuIptIMGNd9AhrThWvscwe4DKD3mzFgDa++y0Yp8sykiWxnDXtDiP+IiZ2VPATmqgO71t7/NFONnLdvdJJmLTAETGsKIwVe4wdlRa6PzA3BmS27g6dfekDVYqHFMWH5exLJtIblInq4iTbmi3BHGCJtb6rXTHe/wBv/wCWqDD/AOjudDqj6mls5BE7b315qvGYV9LvdwQYNiDzAIzEQdUdA/lF2977+q4jiTyXQSSMwsT1j5INtCvmfJZBPddoQbC8Nh3kPNXvwIJkUmuN7tLi2AL5iXHKdPsKGNoN7KmcrZLJJgTMm8oRxRo7ciLR9SrFwVbXY09mWsaDJ1LmnrN9ltwPD6L3Sw12wJGUk07bAObA9N1kbSaGyAAZ2A/u/QLX7Mn+e8bZNNrG1lpgW/DP0d2jxMiMg+l1dRw72R2TY1kOhzjN5B21Op3SquPbC/8ASD580Rw5lpnkPkFE1lw2MqPc4d0OYYc0zMRNo8f8rX+Jd/TlcdwHeuw5IBXeRi3wSPc0P9gROSWkm5DrE66lCmrur1YJY7KSJDS2dfGT+6vpVqbIilVB5imbxzI7vPVBaOIf2g7zrzuei6DR74tZv1Vw01XHgAZm1AJknKR4SQLbbqh1bDPB/wC2c3PLmMdZzTHmsvFazgSA4iIiCeiA42+LcDcESR1ytv4qX0sgi5+FLS+nle0CCw2eJIuA4AuHr4qg8TpCmWGl2rXAOLe6RIN+68xI0kHlZM+mAbADTQf6ldi2CG2Hp4KauA+KwmDrtLWUzTrRYNyNAvuQIdtoCb8lzlfh2R0SHWmWlrh8NPNdnSotNNzi0SNDAkd4aHZV4PBUjmJpsJiZLWzJEzpzVnQ4upQ8PMhRfSI1F/ED4AK1h73mmr6rYpcOYPzTd3cR5KWY/BVZjHmqJS3nHqnzN/MfgkSmlB//2Q=="
													alt="Image" class="w3-left w3-margin-right"
													style="width: 100px"> <span class="w3-large">
														on May 3rd,2018</span><br> <span>Relaxing on the
														beach</span></li>
												<li class="w3-padding-16"><img
													src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRTNNwNgTxWS5zHhXcR7ZAy3sv8Av_sgztV4H3HqRtrmT3d714e"
													alt="Image" class="w3-left w3-margin-right"
													style="width: 100px"> <span class="w3-large">on
														May 3rd, 2018</span><br> <span>What an amazing view</span></li>
												<li class="w3-padding-16"><img
													src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTJJ6ZluO5N6eTRgKiGlwb2-_ZIYaDtpOb1Y-1xuIRuQAI44BfR4g"
													alt="Image" class="w3-left w3-margin-right"
													style="width: 100px"> <span class="w3-large">on
														May 2nd, 2018</span><br> <span> Discovering Arizona</span></li>
												<li class="w3-padding-16 w3-hide-medium w3-hide-small">
													<img
													src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQpkO_291WOGSxPMmGF3QUq-0R38ziFSZdXqAM3q5RRPl-S6BhG"
													alt="Image" class="w3-left w3-margin-right"
													style="width: 100px"> <span class="w3-large">on
														May 2nd, 2018</span><br> <span>Peace</span>
												</li>
											</ul>
										</div>
										<hr>
										
		</div>
	</div

</body>
									</html>
