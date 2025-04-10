document.addEventListener('DOMContentLoaded', function () {
    const jobContainer = document.getElementById('jobContainer');
    const statusFiltersContainer = document.getElementById('statusFilters');

    // Load the job list from localStorage
    let jobList = JSON.parse(localStorage.getItem('jobList')) || [];

    // Function to render jobs in the jobContainer based on a given filter status
    function displayJobs(filterStatus = "All") {
        jobContainer.innerHTML = ""; // Clear previous job cards

        let filteredJobs = (filterStatus === "All")
            ? jobList
            : jobList.filter(job => job.jobStatus === filterStatus);

        filteredJobs.forEach(job => {
            const col = document.createElement('div');
            col.className = 'col-md-4 mb-3';

            const card = document.createElement('div');
            card.className = 'card p-2 h-100';
            card.innerHTML = `
          <div class="card-body">
            <small class="text-muted">${job.appliedDate || ''}</small>
            <h5 class="card-title mt-2">${job.companyName || 'No Company'}</h5>
            <h6 class="card-subtitle mb-2 text-muted">${job.jobTitle || 'No Title'}</h6>
            <p class="card-text">${job.jobStatus || ''}</p>
          </div>
          <div class="card-footer bg-transparent border-0 d-flex justify-content-between">
            <button class="btn btn-outline-primary btn-sm edit-btn" data-id="${job.id}">Edit</button>
            <button class="btn btn-outline-danger btn-sm delete-btn" data-id="${job.id}">Delete</button>
          </div>
        `;
            col.appendChild(card);
            jobContainer.appendChild(col);
        });
    }

    // Initial display of all jobs
    displayJobs("All");

    // Use the status filter builder from statusFilter.js
    // The callback passed to buildStatusFilters will call displayJobs with the selected status.
    if (typeof window.buildStatusFilters === "function") {
        window.buildStatusFilters(jobList, statusFiltersContainer, displayJobs);
    } else {
        console.error("buildStatusFilters is not defined");
    }
});
