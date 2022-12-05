<jsp:include page="components/header.jsp" />
<jsp:include page="components/navigation.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
    .main_footer {
        display: none !important;
    }
</style>
<div class="banner">
    <div class="banner_title" data-aos="fade-up" data-aos-duration="1000">Online<br>Pharmacy</div>
    <div class="search_menu" data-aos="fade-up" data-aos-delay="1000" data-aos-duration="1000">
        <div class="search_box">
            <div class="search_input">
                <input type="text" class="search_field" placeholder="Looking for...">
            </div>
            <div class="search_results">
            </div>
        </div>
    </div>
</div>
<div class="latest_medicines cnt">
    <div class="title">Latest medicines list</div>
    <div class="main_list">
        <c:forEach items="${medicines}" var="medicine">
            <div class="list_item">
                <div class="m_info">
                    <div class="m_title"><a href="/pharmacy/${medicine.getSlug()}">${medicine.getTitle()}</a></div>
                    <div class="m_description">${medicine.getDescription()}</div>
                </div>
                <div class="m_actions">
                    <div class="m_price">${medicine.getPrice()}</div>
                </div>
            </div>
        </c:forEach>
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
<script>
    document.querySelector('.search_field').addEventListener('input', () => {
        var red_shadow = "rgb(181 66 98) -1px 1px 10px"
        var green_shadow = "rgb(117 255 71) -1px 1px 10px"
        var usual  = "rgb(207 207 209) -1px 1px 10px"
        document.querySelector('.search_field').style.boxShadow = red_shadow;
        var value = document.querySelector('.search_field').value;
        if (value.length > 3) {
            fetch('http://localhost:8080/api/medicines?search=' + value)
                .then(response => response.json())
                .then(data => {
                    var medicine_list = data.data;
                    var timer = 100
                    if (medicine_list.length) {
                        document.querySelector('.search_field').style.boxShadow = green_shadow;
                    }
                    medicine_list.forEach(element => {
                        document.querySelector('.search_results').innerHTML +=
                            `<a href="pharmacy/\${element.slug}"  data-aos="fade-up" data-aos-delay="\${timer}" class="med_links">
                                    <div class="search_item">
                                        <div class="med_title">\${element.title}</div>
                                    </div>
                                </a>`;
                        timer+=100
                    });
                });
            document.querySelector('.search_results').innerHTML = "";
        } else {
            document.querySelector('.search_results').innerHTML = "";
            if (value.length == 0) {
                document.querySelector('.search_field').style.boxShadow = usual;
            } else {
                document.querySelector('.search_field').style.boxShadow = red_shadow;
            }
        }
    })
    document.querySelectorAll('.m_description').forEach(e => { if (e.innerText.length > 100) { e.innerText = e.innerText.substring(0, 80)+"..."}})
</script>
<jsp:include page="components/footer.jsp" />