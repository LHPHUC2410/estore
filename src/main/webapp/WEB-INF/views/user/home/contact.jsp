<%@ page pageEncoding="UTF-8"%>

<html>
	<head>
		<style>
			body {
			  font-family: Arial, Helvetica, sans-serif;
			  margin: 0;
			}
			
			html {
			  box-sizing: border-box;
			}
			
			*, *:before, *:after {
			  box-sizing: inherit;
			}
			
			.column {
			  float: left;
			  width: 33.3%;
			  margin-bottom: 16px;
			  padding: 0 8px;
			}
			
			.card {
			  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
			  margin: 8px;
			  padding: 0 16px;
			}
			
			.container::after, .row::after {
			  content: "";
			  clear: both;
			  display: table;
			}
			
			.title {
			  color: grey;
			}
			
			.button {
			  border: none;
			  outline: 0;
			  display: inline-block;
			  padding: 8px;
			  color: white;
			  background-color: #000;
			  text-align: center;
			  cursor: pointer;
			  width: 100%;
			}
			
			.button:hover {
			  background-color: #555;
			}
			
			@media screen and (max-width: 650px) {
			  .column {
			    width: 100%;
			    display: block;
			  }
			}
		</style>
	</head>
</html>



<h2 style="text-align:center">Our Team</h2>
<div class="row">
  <div class="col-sm-4">
    <div class="card">
      <img src="https://th.bing.com/th/id/OIP.lXuMr3dxbAPT0ucLQIMafQHaF0?w=199&h=180&c=7&r=0&o=5&dpr=2&pid=1.7" alt="hung" style="width:100%">
      <div>
        <h2>Phan Phi Hùng</h2>
        <p class="title">CEO & Founder</p>
        <p>Gánh tạ</p>
        <p>jane@example.com</p>
        <button class="btn btn-sm btn-warning btn-star">Contact</button> 
      </div>
    </div>
  </div>

  <div class="col-sm-4">
    <div class="card">
      <img src="https://th.bing.com/th/id/OIP.AZ_J-PVLzas__sgMbG5P7QHaE8?w=235&h=180&c=7&r=0&o=5&dpr=2&pid=1.7" alt="phuc" style="width:100%">
      <div>
        <h2>Lê Hồng Phúc</h2>
        <p class="title">Art Director</p>
        <p>Cục tạ số 1</p>
        <p>mike@example.com</p>
        <button class="btn btn-sm btn-warning btn-star">Contact</button>
      </div>
    </div>
  </div>

  <div class="col-sm-4">
    <div class="card">
      <img src="https://th.bing.com/th/id/OIP.ADA-vGQMw0K3Bzbn9ZOhPgHaE8?rs=1&pid=ImgDetMain" alt="dung" style="width:100%">
      <div>
        <h2>Trần Bá Dũng</h2>
        <p class="title">Designer</p>
        <p>Cục tạ số 2</p>
        <p>john@example.com</p>
        <button class="btn btn-sm btn-warning btn-star">Contact</button>
      </div>
    </div>
  </div>
</div>