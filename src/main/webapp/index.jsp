<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pharmacy</title>
    <link rel="stylesheet" href="/css/styles.css">
    <link rel="stylesheet" href="https://unpkg.com/aos@next/dist/aos.css" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@fortawesome/fontawesome-free@6.2.0/css/fontawesome.min.css" integrity="sha384-z4tVnCr80ZcL0iufVdGQSUzNvJsKjEtqYZjiQrrYKlpGow+btDHDfQWkFjoaz/Zr" crossorigin="anonymous">
</head>
<body>
    <div class="container-flex">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="#">Pharmacy+</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
              <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                  <a class="nav-link" href="#">Main</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="#">About</a>
                </li>
                <li class="nav-item dropdown">
                  <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Login/Register
                  </a>
                  <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="#">Action</a>
                    <a class="dropdown-item" href="#">Another action</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="#">Something else here</a>
                  </div>
                </li>
              </ul>
            </div>
          </nav>
        </div>
        <div class="banner">
            <div class="banner_title" data-aos="fade-up" data-aos-duration="1000">Online<br>Pharmacy</div>
            <div class="search_menu" data-aos="fade-up" data-aos-duration="1000">
                <div class="search_box">
                    <div class="search_input">
                        <input type="text" name="" id="" placeholder="Looking for...">
                    </div>
                    <div class="search_btn">
                        <button type="button" class="btn btn-primary">Search</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="latest_medicines cnt">
            <div class="title">Latest medicines list</div>
            <div class="main_list">
                <div class="list_item">
                    <div class="m_info">
                        <div class="m_title">Teraflyu</div>
                        <div class="m_description">Lorem ipsum dolor, sit amet consectetur adipisicing elit.</div>
                    </div>
                    <div class="m_actions">
                        <div class="m_price">3</div>
                        <div class="m_get"><button type="button" class="btn btn-primary">Order</button></div>
                    </div>
                </div>

                <div class="list_item">
                    <div class="m_info">
                        <div class="m_title">Teraflyu</div>
                        <div class="m_description">Lorem ipsum dolor, sit amet consectetur adipisicing elit.</div>
                    </div>
                    <div class="m_actions">
                        <div class="m_price">3</div>
                        <div class="m_get"><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" data-whatever="@mdo">Order</button></div>
                    </div>
                </div>
                
            </div>
        </div>

        <!-- Modals -->
        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Order terafly</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                </div>
                <div class="modal-body">
                <form>
                    <div class="form-group">
                    <label for="recipient-name" class="col-form-label">Quantity:</label>
                        <input type="number" class="form-control" id="recipient-name" value="1">
                    </div>
                    <div class="form-group">
                    <label for="message-text" class="col-form-label">Dosage:</label>
                        <input type="number" class="form-control" id="recipient-name">
                    </div>
                    <div class="total_price">
                        Total: 123$
                    </div>
                </form>
                </div>
                <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal">Order</button>
                </div>
            </div>
            </div>
        </div>
        <!-- Modals -->

        <footer class="text-center text-lg-start bg-light text-muted tobottom">
            <div class="text-center p-4" style="background-color: rgba(0, 0, 0, 0.05);">
              © 2021 Copyright:
              <a class="text-reset fw-bold" href="/">Pharmacy+</a>
            </div>
            <!-- Copyright -->
          </footer>
<script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js" integrity="sha384-+sLIOodYLS7CIrQpBjl+C7nPvqq+FbNUBDunl/OZv93DB7Ln/533i8e/mZXLi/P+" crossorigin="anonymous"></script>
<script src="https://unpkg.com/aos@next/dist/aos.js"></script>
<script>
    AOS.init();
</script>
</body>
</html>