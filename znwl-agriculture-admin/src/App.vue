<template>
    <div id="app" style="background-color:#0e2e87" v-if="$route.meta.bigScreen">
        <router-view />
        <AUDia />
    </div>
    <div id="app" v-else>
        <router-view />
        <AUDia />
    </div>
</template>

<script>
export default {
    name: 'App',
    metaInfo() {
        return {
            title: this.$store.state.settings.dynamicTitle && this.$store.state.settings.title,
            titleTemplate: title => {
                return title ? `${title} - ${process.env.VUE_APP_TITLE}` : process.env.VUE_APP_TITLE
            }
        }
    }
}
</script>