<template>
  <div>
    <b-button v-b-toggle.sidebar-footer>Toggle Sidebar</b-button>
    <b-sidebar
      visible="true"
      id="sidebar-footer"
      aria-label="Sidebar with custom footer"
      no-header
      shadow
    >
      <template #footer="{ hide }">
        <div class="d-flex bg-dark text-light align-items-center px-3 py-2">
          <strong class="mr-auto">Footer</strong>
          <b-button size="sm" @click="hide">Close</b-button>
        </div>
      </template>
      <div class="px-3 py-2">
        <p>
          잘먹는 사람이 일도 잘한다!
        </p>
        <h3>'잇(Eat)케스트로'</h3>
        <b-divider></b-divider>
        <v-divider></v-divider>
        <diveider></diveider>

        <nav class="mb-3">
          <b-nav vertical>
            <b-nav-item active link to="/dashBoard">대쉬보드</b-nav-item>
            <b-nav-item link to="/storeHome">맛집 게시판</b-nav-item>
            <b-nav-item link to="/dietHome">다이어트 게시판</b-nav-item>
          </b-nav>
        </nav>
        <b-img
          src="https://picsum.photos/500/500/?image=54"
          fluid
          thumbnail
        ></b-img>
      </div>
    </b-sidebar>
  </div>
</template>

<script>
export default {};
</script>

<style></style>
