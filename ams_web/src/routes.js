import Login from './views/Login.vue'
import NotFound from './views/404.vue'
import Home from './views/Home.vue'
import Main from './views/Main.vue'
import Table from './views/nav1/Table.vue'
import Form from './views/nav1/Form.vue'
import user from './views/nav1/user.vue'

import Role from './views/ams/Role.vue'
import Permission from './views/ams/Permission.vue'
import Menu from './views/ams/Menu.vue'

import Page4 from './views/nav2/Page4.vue'
import Page5 from './views/nav2/Page5.vue'
import Page6 from './views/nav3/Page6.vue'
import echarts from './views/charts/echarts.vue'
import DestroyDoc from './views/fileAppraisal/DestroyDoc'
import ProblematicDoc from './views/fileAppraisal/ProblematicDoc'
import User from './views/ams/user.vue'
import RecordType from './views/ams/RecordType.vue'
import Site from './views/ams/Site.vue'
import Record from './views/ams/Record.vue'
import Borrow from './views/ams/Borrow.vue'
import Return from './views/ams/Return.vue'
import BorrowRecord from './views/ams/BorrowRecord.vue'
import Expire from './views/ams/Expire.vue'
import Department from './views/ams/department.vue'

let routes = [
    {
        path: '/login',
        component: Login,
        name: '',
        hidden: true
    },
    {
        path: '/recordtype',
        component:Home,
        name: '档案模块',
        iconCls: 'el-icon-tickets',//图标样式class
        children: [
            { path: '/record', component: Record, name: '档案列表' },
            { path: '/recordtype', component: RecordType, name: '档案管理' },
            { path: '/site', component: Site, name: '档案地点' }
        ]
    },
    {
        path: '/',
        component: Home,
        name: '借阅管理',
        iconCls: 'el-icon-message',//图标样式class
        children: [
            { path: '/borrowRecord', component: BorrowRecord, name: '档案借阅' },
            { path: '/return', component: Return, name: '档案归还' },
            { path: '/borrow', component: Borrow, name: '借阅查询' },
            { path: '/expire', component: Expire, name: '过期管理' }
        ]
    },
    {
        path: '/',
        component: Home,
        name: '档案鉴定',
        iconCls: 'el-icon-s-check',//图标样式class
        children: [
            { path: '/destroyDoc', component: DestroyDoc, name: '销毁登记' },
            { path: '/problematicDoc', component: ProblematicDoc, name: '损坏丢失' },
        ]
    },
    {
        path: '/',
        component: Home,
        name: '系统管理',
        iconCls: 'el-icon-setting',//图标样式class
        children: [
            { path: '/role', component: Role, name: '角色管理' },
            { path: '/permission', component: Permission, name: '权限管理' },
            { path: '/menu', component: Menu, name: '菜单管理' },
            { path: '/user', component: User, name: '用户管理' },
            { path: '/department', component: Department, name: '部门管理' }
        ]
    },
  /*  {
        path:"/role",
        component:Role,
        name:"角色列表",
        hidden:false
    },*/
    {
        path:"/permission",
        component:Permission,
        name:"权限列表",
        hidden:true
    },
    {
        path:"/menu",
        component:Menu,
        name:"菜单列表",
        hidden:true
    },
    {
        path: '/404',
        component: NotFound,
        name: '',
        hidden: true
    }

    //{ path: '/main', component: Main },
    /*{
        path: '/',
        component: Home,
        name: '组织机构管理',
        iconCls: 'el-icon-message',//图标样式class
        children: [
            { path: '/user', component: User, name: '用户管理' },
        ]
    },*/


    /*
    {
        path: '/',
        component: Home,
        name: '导航一',
        iconCls: 'el-icon-message',//图标样式class
        children: [
            { path: '/main', component: Main, name: '主页', hidden: true },
            { path: '/table', component: Table, name: 'Table' },
            { path: '/form', component: Form, name: 'Form' },
            { path: '/user', component: user, name: '列表' },
        ]
    },*/
   /* {
        path: '/',
        component: Home,
        name: '导航二',
        iconCls: 'fa fa-id-card-o',
        children: [
            { path: '/page4', component: Page4, name: '页面4' },
            { path: '/page5', component: Page5, name: '页面5' }
        ]
    },*/
    /*{
        path: '/',
        component: Home,
        name: '',
        iconCls: 'fa fa-address-card',
        leaf: true,//只有一个节点
        children: [
            { path: '/page6', component: Page6, name: '导航三' }
        ]
    },*/
   /* {
        path: '/',
        component: Home,
        name: 'Charts',
        iconCls: 'fa fa-bar-chart',
        children: [
            { path: '/echarts', component: echarts, name: 'echarts' }
        ]
    },*/
   /* {
        path: '/',
        component: Home,
        name: '档案鉴定',
        iconCls: 'el-icon-s-check',//图标样式class
        children: [
            { path: '/destroyDoc', component: DestroyDoc, name: '销毁登记' },
            { path: '/problematicDoc', component: ProblematicDoc, name: '损坏丢失' },
        ]
    },*/
    /*
    {
        path: '*',
        hidden: true,
        redirect: { path: '/404' }
    }*/
];

export default routes;