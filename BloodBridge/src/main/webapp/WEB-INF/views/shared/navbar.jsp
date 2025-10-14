<nav style="display: flex; align-items: center; justify-content: space-between; padding: 1rem; background: #f5f5f5;">
    <div style="font-weight: bold; font-size: 1.5rem;">
        <!-- Logo Placeholder -->
        LOGO
    </div>
    <ul style="list-style: none; display: flex; gap: 2rem; margin: 0; padding: 0;">
        <li>
            <a href="${pageContext.request.contextPath}/dashboard">Dashboard</a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/donors/list">Donors</a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/receivers/list">Receiver</a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/blood">Blood Match</a>
        </li>
    </ul>
    <div>
        <button style="padding: 0.5rem 1rem; background: #d32f2f; color: #fff; border: none; border-radius: 4px; cursor: pointer;">
            Become Donor
        </button>
    </div>
</nav>