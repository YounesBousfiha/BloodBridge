 const openModal = document.getElementById('openModal');
    const closeModal = document.getElementById('closeModal');
    const modal = document.getElementById('simpleModal');

    openModal.addEventListener('click', () => {
    modal.classList.remove('hidden');
});

    closeModal.addEventListener('click', () => {
    modal.classList.add('hidden');
});