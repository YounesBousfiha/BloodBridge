<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="/WEB-INF/views/shared/header.jsp" %>
<%@ include file="/WEB-INF/views/shared/navbar.jsp" %>

<section class="bg-blue-50 py-8">
    <div class="max-w-full mx-auto px-4 sm:px-6 lg:px-8">
        <h2 class="text-2xl font-bold text-blue-700 mb-6">Receiver List</h2>

        <button id="openModal" class="bg-blue-700 hover:bg-blue-800 text-white font-semibold px-4 py-2 rounded mb-4 shadow transition-colors duration-150">
            Add Receiver
        </button>

        <!-- Modal -->
        <div id="simpleModal" class="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50 hidden z-50">
            <div class="bg-white rounded-lg shadow-lg w-full max-w-lg p-6 relative">
                <button id="closeModal" class="absolute top-2 right-2 text-gray-500 hover:text-gray-700">&times;</button>
                <h3 class="text-xl font-bold mb-4 text-blue-700">Add New Receiver</h3>
                <form action="${pageContext.request.contextPath}/receivers/create" method="POST" class="space-y-4">
                    <div>
                        <label class="block text-sm font-medium text-gray-700">First Name</label>
                        <input type="text" name="firstName" required class="mt-1 block w-full border border-gray-300 rounded-md p-2" />
                    </div>
                    <div>
                        <label class="block text-sm font-medium text-gray-700">Last Name</label>
                        <input type="text" name="lastName" required class="mt-1 block w-full border border-gray-300 rounded-md p-2" />
                    </div>
                    <div>
                        <label class="block text-sm font-medium text-gray-700">CIN</label>
                        <input type="text" name="cin" required class="mt-1 block w-full border border-gray-300 rounded-md p-2" />
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
                    <div>
                        <label for="urgentReceveur" class="block text-sm font-medium text-gray-700">Urgency Level</label>
                        <select id="urgentReceveur" name="urgentReceveur" required class="mt-1 block w-full border border-gray-300 rounded-lg shadow-sm p-2">
                            <option value="">Select</option>
                            <option value="CRITICAL">Critical</option>
                            <option value="URGENT">Urgent</option>
                            <option value="NORMAL">Normal</option>
                        </select>
                    </div>
                    <div>
                        <label class="block text-sm font-medium text-gray-700">Phone</label>
                        <input type="text" name="telephone" required class="mt-1 block w-full border border-gray-300 rounded-md p-2" />
                    </div>
                    <div>
                        <label class="block text-sm font-medium text-gray-700">Weight (kg)</label>
                        <input type="number" name="weight" min="0" required class="mt-1 block w-full border border-gray-300 rounded-md p-2" />
                    </div>
                    <div>
                        <label class="block text-sm font-medium text-gray-700">Birth Date</label>
                        <input type="date" name="dateDeNaissance" required class="mt-1 block w-full border border-gray-300 rounded-md p-2" />
                    </div>
                    <div class="flex justify-end">
                        <button type="submit" class="bg-blue-700 text-white px-4 py-2 rounded">Add Receiver</button>
                    </div>
                </form>
            </div>
        </div>

        <div class="overflow-x-auto">
            <!-- Fixed: Table classes for Tailwind, removed border-radius from the table (use container div if needed) -->
            <table class="w-full min-w-[1200px] bg-white border border-gray-200">
                <thead class="bg-blue-100">
                <tr>
                    <th class="px-4 py-2 text-left text-sm font-semibold text-blue-700">ID</th>
                    <th class="px-4 py-2 text-left text-sm font-semibold text-blue-700">Name</th>
                    <th class="px-4 py-2 text-left text-sm font-semibold text-blue-700">CIN</th>
                    <th class="px-4 py-2 text-left text-sm font-semibold text-blue-700">Blood Type</th>
                    <th class="px-4 py-2 text-left text-sm font-semibold text-blue-700">Phone</th>
                    <th class="px-4 py-2 text-left text-sm font-semibold text-blue-700">Weight</th>
                    <th class="px-4 py-2 text-left text-sm font-semibold text-blue-700">Birth Date</th>
                    <th class="px-4 py-2 text-left text-sm font-semibold text-blue-700">Status</th>
                    <th class="px-4 py-2 text-left text-sm font-semibold text-blue-700">Required bags</th>
                    <th class="px-4 py-2 text-left text-sm font-semibold text-blue-700">Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="receiver" items="${receivers}">
                    <tr class="border-t hover:bg-blue-50 transition-colors">
                        <td class="px-4 py-2 text-sm text-gray-700">${receiver.id}</td>
                        <td class="px-4 py-2 text-sm text-gray-700">${receiver.firstName} ${receiver.lastName}</td>
                        <td class="px-4 py-2 text-sm text-gray-700">${receiver.cin}</td>
                        <td class="px-4 py-2 text-sm text-gray-700">${receiver.bloodType}</td>
                        <td class="px-4 py-2 text-sm text-gray-700">${receiver.phoneNumber}</td>
                        <td class="px-4 py-2 text-sm text-gray-700">${receiver.weight}kg</td>
                        <td class="px-4 py-2 text-sm text-gray-700">${receiver.dateOfBirth}</td>
                        <td class="px-4 py-2 text-sm text-gray-700">${receiver.statusReceiver}</td>
                        <td class="px-4 py-2 text-sm text-gray-700">${receiver.requiredBags}  </td>
                        <td class="flex items-center justify-center space-x-6">
                            <!-- Eye icon for details -->
                            <button
                                    class="text-gray-700 hover:text-red-700 flex items-center donor-detail-btn"
                                    title="View Details"
                                    data-id="${receiver.id}"
                                    data-firstname="${receiver.firstName}"
                                    data-lastname="${receiver.lastName}"
                                    data-cin="${receiver.cin}"
                                    data-bloodtype="${receiver.bloodType}"
                                    data-phonenumber="${receiver.phoneNumber}"
                                    data-weight="${receiver.weight}"
                                    data-dateofbirth="${receiver.dateOfBirth}"
                                    data-status="${receiver.statusReceiver}"
                                    data-requiredbags="${receiver.requiredBags}">
                                <i class="fas fa-eye text-lg"></i>
                            </button>
                            <button
                                    type="button"
                                    title="Edit"
                                    class="text-blue-600 hover:text-blue-800 flex items-center donor-edit-btn"
                                    data-id="${receiver.id}"
                                    data-firstname="${receiver.firstName}"
                                    data-lastname="${receiver.lastName}"
                                    data-cin="${receiver.cin}"
                                    data-bloodtype="${receiver.bloodType}"
                                    data-phonenumber="${receiver.phoneNumber}"
                                    data-weight="${receiver.weight}"
                                    data-dateofbirth="${receiver.dateOfBirth}"
                                    data-status="${receiver.statusReceiver}"
                                    data-requiredbags="${receiver.requiredBags}">
                                <i class="fas fa-edit text-lg"></i>
                            </button>
                            <form action="${pageContext.request.contextPath}/receivers/delete"  method="POST" class="delete-donor-form" onsubmit="return confirm('Are you sure you want to delete this receiver?');">
                                <input type="hidden" name="receiverId" value="${receiver.id}">
                                <button type="submit" title="Delete" class="text-red-600 hover:text-red-800 flex items-center delete-donor-btn">
                                    <i class="fas fa-trash-alt text-lg"></i>
                                </button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</section>
<!-- View Details Modal -->
<div id="viewDetailsModal" class="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50 hidden z-50">
    <div class="bg-white rounded-lg shadow-lg w-full max-w-lg p-6 relative">
        <button id="closeViewDetailsModal" class="absolute top-2 right-2 text-gray-500 hover:text-gray-700">&times;</button>
        <h3 class="text-xl font-bold mb-4 text-blue-700">Receiver Details</h3>
        <div class="space-y-2">
            <div><span class="font-semibold">ID:</span> <span id="detail-id"></span></div>
            <div><span class="font-semibold">Name:</span> <span id="detail-name"></span></div>
            <div><span class="font-semibold">CIN:</span> <span id="detail-cin"></span></div>
            <div><span class="font-semibold">Blood Type:</span> <span id="detail-bloodtype"></span></div>
            <div><span class="font-semibold">Phone:</span> <span id="detail-phonenumber"></span></div>
            <div><span class="font-semibold">Weight:</span> <span id="detail-weight"></span></div>
            <div><span class="font-semibold">Birth Date:</span> <span id="detail-dateofbirth"></span></div>
            <div><span class="font-semibold">Status:</span> <span id="detail-status"></span></div>
            <div><span class="font-semibold">Required Bags:</span> <span id="detail-requiredbags"></span></div>
        </div>
    </div>
</div>
<!-- Edit Modal -->
<div id="editReceiverModal" class="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50 hidden z-50">
    <div class="bg-white rounded-lg shadow-lg w-full max-w-lg p-6 relative">
        <button id="closeEditReceiverModal" class="absolute top-2 right-2 text-gray-500 hover:text-gray-700">&times;</button>
        <h3 class="text-xl font-bold mb-4 text-blue-700">Edit Receiver</h3>
        <form id="editReceiverForm" action="${pageContext.request.contextPath}/receivers/edit" method="POST" class="space-y-4">
            <input type="hidden" name="id" id="edit-id" />
            <div>
                <label class="block text-sm font-medium text-gray-700">First Name</label>
                <input type="text" name="firstName" id="edit-firstname" required class="mt-1 block w-full border border-gray-300 rounded-md p-2" />
            </div>
            <div>
                <label class="block text-sm font-medium text-gray-700">Last Name</label>
                <input type="text" name="lastName" id="edit-lastname" required class="mt-1 block w-full border border-gray-300 rounded-md p-2" />
            </div>
            <div>
                <label class="block text-sm font-medium text-gray-700">CIN</label>
                <input type="text" name="cin" id="edit-cin" required class="mt-1 block w-full border border-gray-300 rounded-md p-2" />
            </div>
            <div>
                <label class="block text-sm font-medium text-gray-700">Blood Type</label>
                <select name="bloodType" id="edit-bloodtype" class="mt-1 block w-full border border-gray-300 rounded-lg shadow-sm p-2" required>
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
            <div>
                <label class="block text-sm font-medium text-gray-700">Phone</label>
                <input type="text" name="telephone" id="edit-phonenumber" required class="mt-1 block w-full border border-gray-300 rounded-md p-2" />
            </div>
            <div>
                <label class="block text-sm font-medium text-gray-700">Weight (kg)</label>
                <input type="number" name="weight" id="edit-weight" min="0" required class="mt-1 block w-full border border-gray-300 rounded-md p-2" />
            </div>
            <div>
                <label class="block text-sm font-medium text-gray-700">Birth Date</label>
                <input type="date" name="dateDeNaissance" id="edit-dateofbirth" required class="mt-1 block w-full border border-gray-300 rounded-md p-2" />
            </div>
            <div>
                <label class="block text-sm font-medium text-gray-700">Status</label>
                <input type="text" name="statusReceiver" id="edit-status" required class="mt-1 block w-full border border-gray-300 rounded-md p-2" />
            </div>
            <div>
                <label class="block text-sm font-medium text-gray-700">Required Bags</label>
                <input type="number" name="requiredBags" id="edit-requiredbags" min="0" required class="mt-1 block w-full border border-gray-300 rounded-md p-2" />
            </div>
            <div class="flex justify-end">
                <button type="submit" class="bg-blue-700 text-white px-4 py-2 rounded">Save Changes</button>
            </div>
        </form>
    </div>
</div>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        // Modal open/close logic
        const openModalBtn = document.getElementById('openModal');
        const closeModalBtn = document.getElementById('closeModal');
        const modal = document.getElementById('simpleModal');

        openModalBtn.addEventListener('click', function () {
            modal.classList.remove('hidden');
        });

        closeModalBtn.addEventListener('click', function () {
            modal.classList.add('hidden');
        });

        // Optional: Close modal when clicking outside modal content
        modal.addEventListener('click', function (e) {
            if (e.target === modal) {
                modal.classList.add('hidden');
            }
        });

        // View Details Modal logic
        const viewDetailsModal = document.getElementById('viewDetailsModal');
        const closeViewDetailsModal = document.getElementById('closeViewDetailsModal');
        document.querySelectorAll('.donor-detail-btn').forEach(btn => {
            btn.addEventListener('click', function () {
                document.getElementById('detail-id').textContent = btn.getAttribute('data-id');
                document.getElementById('detail-name').textContent = btn.getAttribute('data-firstname') + ' ' + btn.getAttribute('data-lastname');
                document.getElementById('detail-cin').textContent = btn.getAttribute('data-cin');
                document.getElementById('detail-bloodtype').textContent = btn.getAttribute('data-bloodtype');
                document.getElementById('detail-phonenumber').textContent = btn.getAttribute('data-phonenumber');
                document.getElementById('detail-weight').textContent = btn.getAttribute('data-weight') + 'kg';
                document.getElementById('detail-dateofbirth').textContent = btn.getAttribute('data-dateofbirth');
                document.getElementById('detail-status').textContent = btn.getAttribute('data-status');
                document.getElementById('detail-requiredbags').textContent = btn.getAttribute('data-requiredbags');
                viewDetailsModal.classList.remove('hidden');
            });
        });
        closeViewDetailsModal.addEventListener('click', function () {
            viewDetailsModal.classList.add('hidden');
        });
        viewDetailsModal.addEventListener('click', function (e) {
            if (e.target === viewDetailsModal) {
                viewDetailsModal.classList.add('hidden');
            }
        });

        // Edit Modal logic
        const editReceiverModal = document.getElementById('editReceiverModal');
        const closeEditReceiverModal = document.getElementById('closeEditReceiverModal');
        document.querySelectorAll('.donor-edit-btn').forEach(btn => {
            btn.addEventListener('click', function () {
                document.getElementById('edit-id').value = btn.getAttribute('data-id');
                document.getElementById('edit-firstname').value = btn.getAttribute('data-firstname');
                document.getElementById('edit-lastname').value = btn.getAttribute('data-lastname');
                document.getElementById('edit-cin').value = btn.getAttribute('data-cin');
                document.getElementById('edit-bloodtype').value = btn.getAttribute('data-bloodtype');
                document.getElementById('edit-phonenumber').value = btn.getAttribute('data-phonenumber');
                document.getElementById('edit-weight').value = btn.getAttribute('data-weight');
                document.getElementById('edit-dateofbirth').value = btn.getAttribute('data-dateofbirth');
                document.getElementById('edit-status').value = btn.getAttribute('data-status');
                document.getElementById('edit-requiredbags').value = btn.getAttribute('data-requiredbags');
                editReceiverModal.classList.remove('hidden');
            });
        });
        closeEditReceiverModal.addEventListener('click', function () {
            editReceiverModal.classList.add('hidden');
        });
        editReceiverModal.addEventListener('click', function (e) {
            if (e.target === editReceiverModal) {
                editReceiverModal.classList.add('hidden');
            }
        });

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
<%@ include file="/WEB-INF/views/shared/footer.jsp" %>
