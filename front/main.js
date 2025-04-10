document.addEventListener('DOMContentLoaded', function () {
    console.log("main.js loaded");

    const jobContainer = document.getElementById('jobContainer');
    let jobList = JSON.parse(localStorage.getItem('jobList')) || [];
    console.log("Jobs loaded from localStorage:", jobList);

    // A helper function to create a job card
    function createJobCard(job) {
        // Wrap card in a Bootstrap column layout
        const cardCol = document.createElement('div');
        cardCol.className = 'col-4 mb-3'; // 3 cards per row on all sizes

        // Create a card
        const card = document.createElement('div');
        card.className = 'card h-100 p-2 border-1 shadow-sm';

        // Build the card’s inner HTML
        card.innerHTML = `
            <div class="card-body">
                <h5 class="card-title mt-2">${job.companyName || 'No Company'}</h5>
                <h6 class="card-subtitle mb-2 text-muted">${job.jobTitle || 'No Title'}</h6>
                <strong>Status:</strong> ${job.jobStatus || 'N/A'}<br>
                <strong>Applied Date:</strong> ${job.appliedDate || 'N/A'}<br>
                <button class="btn btn-outline-primary btn-sm edit-btn" data-id="${job.id}">Edit</button>
                <button class="btn btn-outline-danger btn-sm delete-btn" data-id="${job.id}">Delete</button>
            </div>
        `;

        cardCol.appendChild(card);
        return cardCol;
    }

    function renderJobs() {
        jobContainer.innerHTML = ''; // Clear existing cards
        jobList.forEach(job => {
            const cardCol = createJobCard(job);
            jobContainer.appendChild(cardCol);
        });
    }

    renderJobs();


    // Listen for clicks on Edit or Delete
    jobContainer.addEventListener('click', function (e) {
        // Delete job
        if (e.target.classList.contains('delete-btn')) {
            const jobId = e.target.dataset.id;
            // Filter out this job from jobList
            jobList = jobList.filter(j => j.id != jobId);
            // Save updated list to localStorage
            localStorage.setItem('jobList', JSON.stringify(jobList));
            // Remove the card from the DOM
            e.target.closest('.col-md-4').remove();
            // Optionally: show a toast or alert that the job was deleted
        }

        // Edit job 
        if (e.target.classList.contains('edit-btn')) {
            const jobId = e.target.dataset.id;
            // Option 1: Open create-job.html in "edit mode"
            //   You might pass the job ID as a query parameter
            //   Example: window.location.href = `create-job.html?editId=${jobId}`;
            // Option 2: Show a modal with job data pre-populated.
            //   This would require a separate function to handle the modal logic.
        }
    });
});
