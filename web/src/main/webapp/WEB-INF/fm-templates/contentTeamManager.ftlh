<div>
  <script src="https://code.jquery.com/jquery-3.4.0.min.js"></script>
  <script src="/js/team.js"></script>
  <article id="contentView">

    <div class="container-fluid">
      <table class="table table-borderless">
        <tr>
          <td style="text-align: left"><h2>Team Manager</h2></td>
          <td style="text-align: right">
            <button type="button" class="btn btn-outline-secondary"
                    data-toggle="modal" data-target="#exampleModal" id="add_team_button">
              <img src="/img/button_add.svg" alt="Add"> Add Team
            </button>
          </td>
        </tr>
      </table>
    </div>

    <div>
        <#if success??>
          <div id="successMessage" class="alert alert-success fade show" role="alert"
               style="text-align: center">
            <strong>
                <#list success>
                    <#items as successMessages>
                        ${successMessages}<br/>
                    </#items>
                </#list>
            </strong>
          </div>
        </#if>
    </div>

    <div id="successMain" class="alert alert-success fade show" role="alert"
         style="text-align: center; display: none;">
    </div>

    <div id="errorsMain" class="alert alert-danger fade show" role="alert"
         style="text-align: center; display: none;">
    </div>

    <table class="table table-sm table-hover" style="text-align: center">

      <thead>
      <tr>
        <th scope="col" style="width: 3%">#</th>
        <th scope="col" style="width: 3%">Id</th>
        <th scope="col" style="width: 86%">Name</th>
        <th scope="col" style="width: 8%">Manage</th>
      </tr>
      </thead>
      <tbody>
      <#list teams>
          <#items as team>
            <tr>
            <tr id="team${team.id}">
              <td>${team_index + 1}.</td>
              <td>${team.id}</td>
              <td>${team.name}</td>
              <td>
                <div>
                  <a class="edit-team" data-id="${team.id}" href="#" data-toggle="modal"
                     data-target="#exampleModal">
                    <img src="/img/button_edit.svg" id="label" alt="Edit" title="Edit"></a>
                  <a class="delete-team" data-id="${team.id}" href="#">
                    <img src="/img/button_delete.svg" alt="Delete" title="Delete"></a>
                </div>
              </td>
            </tr>
          </#items>
      </#list>
      </tbody>
    </table>

    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
         aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="label">
              Team
            </h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>

          <div id="errors" class="alert alert-danger" role="alert"
               style="text-align: center; display: none;">
          </div>

          <div class="modal-body">
            <form id="settingForm" name="contact" role="form">
              <input id="formMethod" type="hidden" name="method" value="${method!"post"}"/>
              <input type="hidden" id="id" name="id" value="${(team.id)!""}"/>
              <div class="form-group">
                <label for="name">Name:
                  <input style="width: 475px" autocomplete="off" type="text" id="name"
                         class="form-control" name="name"
                         placeholder="New team name" value="${(team.name)!""}">
                </label>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-outline-secondary" data-dismiss="modal">Close
            </button>
            <button type="button" class="btn btn-outline-secondary" id="saveTeam">Save changes
            </button>
          </div>
        </div>
      </div>
    </div>

  </article>
</div>