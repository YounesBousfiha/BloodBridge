<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="/WEB-INF/views/shared/header.jsp" %>
<%@ include file="/WEB-INF/views/shared/navbar.jsp" %>

<section class="bg-red-50 py-8">
    <div class="max-w-full mx-auto px-4 sm:px-6 lg:px-8">
        <h2 class="text-2xl font-bold text-red-700 mb-6">Donor List</h2>

        <button id="openModal" class="bg-red-700 text-white px-4 py-2 rounded mb-4">Add Donor</button>

        <div class="overflow-x-auto">
            <table class="w-full min-w-[1200px] bg-white border border-gray-200 rounded-lg">
                <thead class="bg-red-100">
                <tr>
                    <th class="px-4 py-2 text-left text-sm font-semibold text-red-700">ID</th>
                    <th class="px-4 py-2 text-left text-sm font-semibold text-red-700">Name</th>
                    <th class="px-4 py-2 text-left text-sm font-semibold text-red-700">CIN</th>
                    <th class="px-4 py-2 text-left text-sm font-semibold text-red-700">Blood Type</th>
                    <th class="px-4 py-2 text-left text-sm font-semibold text-red-700">Phone</th>
                    <th class="px-4 py-2 text-left text-sm font-semibold text-red-700">Weight</th>
                    <th class="px-4 py-2 text-left text-sm font-semibold text-red-700">Birth Date</th>
                    <th class="px-4 py-2 text-left text-sm font-semibold text-red-700">Created At</th>
                    <th class="px-4 py-2 text-left text-sm font-semibold text-red-700">Last Donation</th>
                    <th class="px-4 py-2 text-left text-sm font-semibold text-red-700">Status</th>
                    <th class="px-4 py-2 text-left text-sm font-semibold text-red-700">Receiver ID</th>
                    <th class="px-4 py-2 text-left text-sm font-semibold text-red-700">Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="donor" items="${donors}">
                    <tr class="border-t">
                        <td class="px-4 py-2 text-sm text-gray-700">${donor.id}</td>
                        <td class="px-4 py-2 text-sm text-gray-700">${donor.firstName} ${donor.lastName}</td>
                        <td class="px-4 py-2 text-sm text-gray-700">${donor.cin}</td>
                        <td class="px-4 py-2 text-sm text-gray-700">${donor.bloodType}</td>
                        <td class="px-4 py-2 text-sm text-gray-700">${donor.telephone}</td>
                        <td class="px-4 py-2 text-sm text-gray-700">${donor.weight}kg</td>
                        <td class="px-4 py-2 text-sm text-gray-700">${donor.dateDeNaissance}</td>
                        <td class="px-4 py-2 text-sm text-gray-700">${donor.createdAt}</td>
                        <td class="px-4 py-2 text-sm text-gray-700">
                            <c:choose>
                                <c:when test="${not empty donor.dernierDon}">
                                    ${donor.dernierDon}
                                </c:when>
                                <c:otherwise>
                                    N/A
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td class="px-4 py-2 text-sm text-gray-700">${donor.statusDonneur}</td>
                        <td class="px-4 py-2 text-sm text-gray-700">
                            <c:choose>
                                <c:when test="${not empty donor.receiverId}">
                                    ${donor.receiverId}
                                </c:when>
                                <c:otherwise>
                                    N/A
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td class="flex items-center justify-center space-x-6">
                            <!-- Eye icon for details -->
                            <button
                                class="text-gray-700 hover:text-red-700 flex items-center donor-detail-btn"
                                title="View Details"
                                data-id="${donor.id}"
                                data-name="${donor.firstName} ${donor.lastName}"
                                data-cin="${donor.cin}"
                                data-bloodtype="${donor.bloodType}"
                                data-phone="${donor.telephone}"
                                data-weight="${donor.weight}"
                                data-birthdate="${donor.dateDeNaissance}"
                                data-createdat="${donor.createdAt}"
                                data-lastdon="${donor.dernierDon}"
                                data-status="${donor.statusDonneur}"
                                data-receiverid="${donor.receiverId}"
                                data-contraindications='<c:out value="${donor.contraindications}" />'
                            >
                                <i class="fas fa-eye text-lg"></i>
                            </button>
                            <button
                                type="button"
                                title="Edit"
                                class="text-blue-600 hover:text-blue-800 flex items-center donor-edit-btn"
                                data-id="${donor.id}"
                                data-firstname="${donor.firstName}"
                                data-lastname="${donor.lastName}"
                                data-cin="${donor.cin}"
                                data-bloodtype="${donor.bloodType}"
                                data-phone="${donor.telephone}"
                                data-weight="${donor.weight}"
                                data-birthdate="${donor.dateDeNaissance}"
                                data-contraindications='<c:out value="${donor.contraindications}" />'
                            >
                                <i class="fas fa-edit text-lg"></i>
                            </button>
                            <form action="${pageContext.request.contextPath}/donors/delete" method="POST" class="delete-donor-form">
                                <input type="hidden" name="donorId" value="${donor.id}">
                                <button type="submit" title="Delete" class="text-red-600 hover:text-red-800 flex items-center delete-donor-btn">
                                    <i class="fas fa-trash-alt text-lg"></i>
                                </button>
                            </form>
                            <button
                                type="button"
                                title="Assign Receiver"
                                class="text-green-600 hover:text-green-800 flex items-center assign-receiver-btn"
                                data-id="${donor.id}"
                            >
                                <i class="fas fa-link text-lg"></i>
                            </button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <div id="simpleModal" class="fixed inset-0 bg-gray-800 bg-opacity-50 flex items-center justify-center hidden">
        <div class="bg-white rounded-lg shadow-lg p-8 w-full max-w-2xl">
            <h3 class="text-2xl font-bold mb-6 text-red-700 text-center">Add Donor</h3>
            <form id="donorForm" action="${pageContext.request.contextPath}/donors/create" method="POST">
                <div class="grid grid-cols-1 sm:grid-cols-2 gap-6 mb-6">
                    <div>
                        <label for="firstName" class="block text-sm font-medium text-gray-700">First Name</label>
                        <input type="text" id="firstName" name="firstName" class="mt-1 block w-full border border-gray-300 rounded-lg shadow-sm focus:ring-red-500 focus:border-red-500 p-2" required>
                    </div>
                    <div>
                        <label for="lastName" class="block text-sm font-medium text-gray-700">Last Name</label>
                        <input type="text" id="lastName" name="lastName" class="mt-1 block w-full border border-gray-300 rounded-lg shadow-sm focus:ring-red-500 focus:border-red-500 p-2" required>
                    </div>
                </div>
                <div class="mb-6">
                    <label for="cin" class="block text-sm font-medium text-gray-700">CIN</label>
                    <input type="text" id="cin" name="cin" class="mt-1 block w-full border border-gray-300 rounded-lg shadow-sm focus:ring-red-500 focus:border-red-500 p-2" required>
                </div>
                <div class="mb-6">
                    <label for="bloodType" class="block text-sm font-medium text-gray-700">Blood Type</label>
                    <select id="bloodType" name="bloodType" class="mt-1 block w-full border border-gray-300 rounded-lg shadow-sm focus:ring-red-500 focus:border-red-500 p-2" required>
                        <option value="">Select</option>
                        <option value="A_PLUS">A+</option>
                        <option value="A_MOIN">A-</option>
                        <option value="B_PLUS">B+</option>
                        <option value="B_MOIN">B-</option>
                        <option value="O_PLUS">O+</option>
                        <option value="O_MOIN">O-</option>
                        <option value="AB_PLUS">AB+</option>
                        <option value="AB_MOIN">AB-</option>
                    </select>
                </div>
                <div class="grid grid-cols-1 sm:grid-cols-2 gap-6 mb-6">
                    <div>
                        <label for="telephone" class="block text-sm font-medium text-gray-700">Phone</label>
                        <input type="tel" id="telephone" name="telephone" class="mt-1 block w-full border border-gray-300 rounded-lg shadow-sm focus:ring-red-500 focus:border-red-500 p-2" required>
                    </div>
                    <div>
                        <label for="weight" class="block text-sm font-medium text-gray-700">Weight (kg)</label>
                        <input type="number" id="weight" name="weight" class="mt-1 block w-full border border-gray-300 rounded-lg shadow-sm focus:ring-red-500 focus:border-red-500 p-2" required>
                    </div>
                </div>
                <div class="mb-6">
                    <label for="dateDeNaissance" class="block text-sm font-medium text-gray-700">Birth Date</label>
                    <input type="date" id="dateDeNaissance" name="dateDeNaissance" class="mt-1 block w-full border border-gray-300 rounded-lg shadow-sm focus:ring-red-500 focus:border-red-500 p-2" required>
                </div>
                <div class="mb-6">
                    <label class="block text-sm font-medium text-gray-700 mb-2">Contraindications</label>
                    <div class="grid grid-cols-2 sm:grid-cols-3 gap-4">
                        <div>
                            <input type="checkbox" id="contra1" name="contraIndications" value="HIV" class="mr-2">
                            <label for="contra1" class="text-sm text-gray-700">HIV</label>
                        </div>
                        <div>
                            <input type="checkbox" id="contra2" name="contraIndications" value="HEPATITE_B" class="mr-2">
                            <label for="contra2" class="text-sm text-gray-700">HEPATITE_B</label>
                        </div>
                        <div>
                            <input type="checkbox" id="contra3" name="contraIndications" value="HEPATITE_C" class="mr-2">
                            <label for="contra3" class="text-sm text-gray-700">HEPATITE_C</label>
                        </div>
                        <div>
                            <input type="checkbox" id="contra3" name="contraIndications" value="SYPHILIS" class="mr-2">
                            <label for="contra3" class="text-sm text-gray-700">SYPHILIS</label>
                        </div>
                        <div>
                            <input type="checkbox" id="contra3" name="contraIndications" value="CANCER" class="mr-2">
                            <label for="contra3" class="text-sm text-gray-700">CANCER</label>
                        </div>
                        <div>
                            <input type="checkbox" id="contra3" name="contraIndications" value="DIABETE_INSULINE" class="mr-2">
                            <label for="contra3" class="text-sm text-gray-700">DIABETE_INSULINE</label>
                        </div>
                        <div>
                            <input type="checkbox" id="contra3" name="contraIndications" value="EPILEPSIE" class="mr-2">
                            <label for="contra3" class="text-sm text-gray-700">EPILEPSIE</label>
                        </div>
                        <div>
                            <input type="checkbox" id="contra3" name="contraIndications" value="AUTO_IMMUNE_DISEASE" class="mr-2">
                            <label for="contra3" class="text-sm text-gray-700">AUTO_IMMUNE_DISEASE</label>
                        </div>
                        <div>
                            <input type="checkbox" id="contra3" name="contraIndications" value="GREFFE" class="mr-2">
                            <label for="contra3" class="text-sm text-gray-700">GREFFE</label>
                        </div>
                        <div>
                            <input type="checkbox" id="contra3" name="contraIndications" value="DROGUE_INJECTABLE" class="mr-2">
                            <label for="contra3" class="text-sm text-gray-700">DROGUE_INJECTABLE</label>
                        </div>
                        <div>
                            <input type="checkbox" id="contra3" name="contraIndications" value="TATOUAGE_RECENT" class="mr-2">
                            <label for="contra3" class="text-sm text-gray-700">TATOUAGE_RECENT</label>
                        </div>
                        <div>
                            <input type="checkbox" id="contra3" name="contraIndications" value="INFECTION_RECENTE" class="mr-2">
                            <label for="contra3" class="text-sm text-gray-700">INFECTION_RECENTE</label>
                        </div>
                        <div>
                            <input type="checkbox" id="contra3" name="contraIndications" value="CHIRURGIE_RECENTE" class="mr-2">
                            <label for="contra3" class="text-sm text-gray-700">CHIRURGIE_RECENTE</label>
                        </div>
                        <div>
                            <input type="checkbox" id="contra3" name="contraIndications" value="VOYAGE_PALUDISME" class="mr-2">
                            <label for="contra3" class="text-sm text-gray-700">VOYAGE_PALUDISME</label>
                        </div>
                        <div>
                            <input type="checkbox" id="contra3" name="contraIndications" value="CARDIAC_DISEASE" class="mr-2">
                            <label for="contra3" class="text-sm text-gray-700">CARDIAC_DISEASE</label>
                        </div>
                        <div>
                            <input type="checkbox" id="contra3" name="contraIndications" value="GROSSESSE_RECENTE" class="mr-2">
                            <label for="contra3" class="text-sm text-gray-700">GROSSESSE_RECENTE</label>
                        </div>
                    </div>
                </div>
                <div class="flex justify-end">
                    <button type="button" id="closeModal" class="bg-gray-500 text-white px-4 py-2 rounded-lg mr-2 hover:bg-gray-600">Cancel</button>
                    <button type="submit" class="bg-red-700 text-white px-4 py-2 rounded-lg hover:bg-red-800">Save</button>
                </div>
            </form>
        </div>
    </div>

    <!-- Edit Donor Modal -->
    <div id="editDonorModal" class="fixed inset-0 bg-gray-800 bg-opacity-50 flex items-center justify-center z-50 hidden">
        <div class="bg-white rounded-lg shadow-lg p-8 w-full max-w-2xl relative">
            <button type="button" id="closeEditDonorModal" class="absolute top-4 right-4 text-gray-400 hover:text-red-600 text-2xl font-bold">&times;</button>
            <h3 class="text-2xl font-bold mb-6 text-blue-700 text-center">Edit Donor</h3>
            <form id="editDonorForm" action="${pageContext.request.contextPath}/donors/edit" method="POST">
                <input type="hidden" id="editDonorId" name="donorId">
                <div class="grid grid-cols-1 sm:grid-cols-2 gap-6 mb-6">
                    <div>
                        <label for="editFirstName" class="block text-sm font-medium text-gray-700">First Name</label>
                        <input type="text" id="editFirstName" name="firstName" class="mt-1 block w-full border border-gray-300 rounded-lg shadow-sm focus:ring-blue-500 focus:border-blue-500 p-2" required>
                    </div>
                    <div>
                        <label for="editLastName" class="block text-sm font-medium text-gray-700">Last Name</label>
                        <input type="text" id="editLastName" name="lastName" class="mt-1 block w-full border border-gray-300 rounded-lg shadow-sm focus:ring-blue-500 focus:border-blue-500 p-2" required>
                    </div>
                </div>
                <div class="mb-6">
                    <label for="editCIN" class="block text-sm font-medium text-gray-700">CIN</label>
                    <input type="text" id="editCIN" name="cin" class="mt-1 block w-full border border-gray-300 rounded-lg shadow-sm focus:ring-blue-500 focus:border-blue-500 p-2" required>
                </div>
                <div class="grid grid-cols-1 sm:grid-cols-2 gap-6 mb-6">
                    <div>
                        <label for="editTelephone" class="block text-sm font-medium text-gray-700">Phone</label>
                        <input type="tel" id="editTelephone" name="telephone" class="mt-1 block w-full border border-gray-300 rounded-lg shadow-sm focus:ring-blue-500 focus:border-blue-500 p-2" required>
                    </div>
                    <div>
                        <label for="editWeight" class="block text-sm font-medium text-gray-700">Weight (kg)</label>
                        <input type="number" id="editWeight" name="weight" class="mt-1 block w-full border border-gray-300 rounded-lg shadow-sm focus:ring-blue-500 focus:border-blue-500 p-2" required>
                    </div>
                    <div>
                        <label for="editdateDeNaissance" class="block text-sm font-medium text-gray-700">Date</label>
                        <input type="date" id="editdateDeNaissance" name="dateDeNaissance" class="mt-1 block w-full border border-gray-300 rounded-lg shadow-sm focus:ring-blue-500 focus:border-blue-500 p-2">
                    </div>
                </div>
                <div class="flex justify-end">
                    <button type="button" id="cancelEditDonor" class="bg-gray-500 text-white px-4 py-2 rounded-lg mr-2 hover:bg-gray-600">Cancel</button>
                    <button type="submit" class="bg-blue-700 text-white px-4 py-2 rounded-lg hover:bg-blue-800">Update</button>
                </div>
            </form>
        </div>
    </div>

    <!-- Elegant Donor Detail Modal -->
    <div id="donorDetailModal" class="fixed inset-0 bg-gray-900 bg-opacity-60 flex items-center justify-center z-50 hidden">
        <div class="bg-white rounded-2xl shadow-2xl p-8 w-full max-w-lg relative">
            <button id="closeDetailModal" class="absolute top-4 right-4 text-gray-400 hover:text-red-600 text-2xl font-bold">&times;</button>
            <h3 class="text-2xl font-bold mb-4 text-red-700 text-center">Donor Details</h3>
            <div class="space-y-2 mb-4">
                <div class="flex justify-between">
                    <span class="font-semibold text-gray-700">Name:</span>
                    <span id="detailName" class="text-gray-900"></span>
                </div>
                <div class="flex justify-between">
                    <span class="font-semibold text-gray-700">CIN:</span>
                    <span id="detailCIN" class="text-gray-900"></span>
                </div>
                <div class="flex justify-between">
                    <span class="font-semibold text-gray-700">Blood Type:</span>
                    <span id="detailBloodType" class="text-gray-900"></span>
                </div>
                <div class="flex justify-between">
                    <span class="font-semibold text-gray-700">Phone:</span>
                    <span id="detailPhone" class="text-gray-900"></span>
                </div>
                <div class="flex justify-between">
                    <span class="font-semibold text-gray-700">Weight:</span>
                    <span id="detailWeight" class="text-gray-900"></span>
                </div>
                <div class="flex justify-between">
                    <span class="font-semibold text-gray-700">Birth Date:</span>
                    <span id="detailBirthDate" class="text-gray-900"></span>
                </div>
                <div class="flex justify-between">
                    <span class="font-semibold text-gray-700">Created At:</span>
                    <span id="detailCreatedAt" class="text-gray-900"></span>
                </div>
                <div class="flex justify-between">
                    <span class="font-semibold text-gray-700">Last Donation:</span>
                    <span id="detailLastDon" class="text-gray-900"></span>
                </div>
                <div class="flex justify-between">
                    <span class="font-semibold text-gray-700">Status:</span>
                    <span id="detailStatus" class="text-gray-900"></span>
                </div>
                <div class="flex justify-between">
                    <span class="font-semibold text-gray-700">Receiver ID:</span>
                    <span id="detailReceiverId" class="text-gray-900"></span>
                </div>
            </div>
            <div>
                <h4 class="text-lg font-semibold text-red-600 mb-2">Contraindications</h4>
                <ul id="detailContraindications" class="list-disc list-inside text-gray-800 space-y-1"></ul>
            </div>
        </div>
    </div>

    <!-- Assign Receiver Modal -->
    <div id="assignReceiverModal" class="fixed inset-0 bg-gray-800 bg-opacity-50 flex items-center justify-center hidden z-50">
        <div class="bg-white rounded-lg shadow-lg p-8 w-80 relative">
            <button type="button" id="closeAssignReceiverModal" class="absolute top-4 right-4 text-gray-400 hover:text-red-600 text-2xl font-bold">&times;</button>
            <h3 class="text-2xl font-bold mb-6 text-green-700 text-center">Assign Receiver</h3>
            <form id="assignReceiverForm" action="${pageContext.request.contextPath}/donors/assign" method="POST">
                <input type="hidden" id="assignDonorId" name="donorId">
                <div class="mb-6">
                    <label for="receiverSelect" class="block text-sm font-medium text-gray-700">Select Receiver</label>
                    <select id="receiverSelect" name="receiverId" class="mt-1 block w-full border border-gray-300 rounded-lg shadow-sm focus:ring-green-500 focus:border-green-500 p-2" required>
                        <option value="">Select Receiver</option>
                        <c:forEach var="receiver" items="${receivers}">
                            <option value="${receiver.id}">${receiver.firstName} ${receiver.lastName} (${receiver.bloodType})</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="flex justify-end">
                    <button type="button" id="cancelAssignReceiver" class="bg-gray-500 text-white px-4 py-2 rounded-lg mr-2 hover:bg-gray-600">Cancel</button>
                    <button type="submit" class="bg-green-700 text-white px-4 py-2 rounded-lg hover:bg-green-800">Assign</button>
                </div>
            </form>
        </div>
    </div>
</section>

<script src="<c:url value='/js/modal.js' />"></script>
<script>
    // Elegant modal logic for donor details
    document.querySelectorAll('.donor-detail-btn').forEach(btn => {
        btn.addEventListener('click', function() {
            document.getElementById('donorDetailModal').classList.remove('hidden');
            document.getElementById('detailName').textContent = this.dataset.name || '';
            document.getElementById('detailCIN').textContent = this.dataset.cin || '';
            document.getElementById('detailBloodType').textContent = this.dataset.bloodtype || '';
            document.getElementById('detailPhone').textContent = this.dataset.phone || '';
            document.getElementById('detailWeight').textContent = this.dataset.weight ? this.dataset.weight + ' kg' : '';
            document.getElementById('detailBirthDate').textContent = this.dataset.birthdate || '';
            document.getElementById('detailCreatedAt').textContent = this.dataset.createdat || '';
            document.getElementById('detailLastDon').textContent = this.dataset.lastdon || 'N/A';
            document.getElementById('detailStatus').textContent = this.dataset.status || '';
            document.getElementById('detailReceiverId').textContent = this.dataset.receiverid || 'N/A';

            // Parse contraindications (assume it's a comma-separated string or array)
            let contra = this.getAttribute('data-contraindications');
            let ul = document.getElementById('detailContraindications');
            ul.innerHTML = '';
            if (contra && contra !== '[]' && contra !== 'None') {
                try {
                    let arr = JSON.parse(contra.replace(/'/g, '"'));
                    if (!Array.isArray(arr)) arr = [arr];
                    arr.forEach(item => {
                        if (item) {
                            let li = document.createElement('li');
                            li.textContent = item;
                            ul.appendChild(li);
                        }
                    });
                } catch {
                    // fallback: comma separated
                    contra.split(',').forEach(item => {
                        if (item.trim()) {
                            let li = document.createElement('li');
                            li.textContent = item.trim();
                            ul.appendChild(li);
                        }
                    });
                }
            } else {
                let li = document.createElement('li');
                li.textContent = 'None';
                ul.appendChild(li);
            }
        });
    });
    document.getElementById('closeDetailModal').onclick = function() {
        document.getElementById('donorDetailModal').classList.add('hidden');
    };
    // Optional: close modal on background click
    document.getElementById('donorDetailModal').addEventListener('click', function(e) {
        if (e.target === this) this.classList.add('hidden');
    });
</script>
<script>
    // Edit Donor Modal logic
    document.querySelectorAll('.donor-edit-btn').forEach(btn => {
        btn.addEventListener('click', function() {
            // Fill modal fields
            document.getElementById('editDonorId').value = this.dataset.id || '';
            document.getElementById('editFirstName').value = this.dataset.firstname || '';
            document.getElementById('editLastName').value = this.dataset.lastname || '';
            document.getElementById('editCIN').value = this.dataset.cin || '';
            document.getElementById('editTelephone').value = this.dataset.phone || '';
            document.getElementById('editWeight').value = this.dataset.weight || '';
            document.getElementById('editdateDeNaissance').value = this.dataset.birthdate || '';

            document.getElementById('editDonorModal').classList.remove('hidden');
        });
    });
    document.getElementById('closeEditDonorModal').onclick = function() {
        document.getElementById('editDonorModal').classList.add('hidden');
    };
    document.getElementById('cancelEditDonor').onclick = function() {
        document.getElementById('editDonorModal').classList.add('hidden');
    };
    document.getElementById('editDonorModal').addEventListener('click', function(e) {
        if (e.target === this) this.classList.add('hidden');
    });
</script>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        const error = '<%= session.getAttribute("error") %>';
        const message = '<%= session.getAttribute("message") %>';

        if ((error === 'null' || error.trim() === '') && (message === 'null' || message.trim() === '')) return;

        if (error !== 'null' && error.trim() !== '') {
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: error,
                confirmButtonColor: '#dc2626', // Red color for OK button
                customClass: {
                    confirmButton: 'swal-confirm-btn'
                }
            });
            <% session.removeAttribute("error"); %>
        } else if (message !== 'null' && message.trim() !== '') {
            Swal.fire({
                icon: 'success',
                title: 'Success',
                text: message,
                confirmButtonColor: '#dc2626',
                customClass: {
                    confirmButton: 'swal-confirm-btn'
                }
            });
            <% session.removeAttribute("message"); %>
        }
    });
</script>
<script>
    // Assign Receiver Modal logic
    document.querySelectorAll('.assign-receiver-btn').forEach(btn => {
        btn.addEventListener('click', function() {
            document.getElementById('assignDonorId').value = this.dataset.id;
            document.getElementById('assignReceiverModal').classList.remove('hidden');
        });
    });
    document.getElementById('closeAssignReceiverModal').onclick = function() {
        document.getElementById('assignReceiverModal').classList.add('hidden');
    };
    document.getElementById('cancelAssignReceiver').onclick = function() {
        document.getElementById('assignReceiverModal').classList.add('hidden');
    };
    document.getElementById('assignReceiverModal').addEventListener('click', function(e) {
        if (e.target === this) this.classList.add('hidden');
    });
</script>
<%@ include file="/WEB-INF/views/shared/footer.jsp" %>