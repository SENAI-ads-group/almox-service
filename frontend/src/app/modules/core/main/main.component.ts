import { Component, AfterViewInit, Renderer2, OnDestroy } from "@angular/core";
import { PrimeNGConfig } from "primeng/api";

@Component({
    selector: "app-main",
    templateUrl: "./main.component.html",
})
export class MainComponent implements AfterViewInit, OnDestroy {
    activeTabIndex: number;

    sidebarActive: boolean;

    topbarMenuActive: boolean;

    sidebarClick: boolean;

    topbarItemClick: boolean;

    activeTopbarItem: any;

    documentClickListener: any;

    configActive: boolean;

    configClick: boolean;

    constructor(
        public renderer: Renderer2,
        private primengConfig: PrimeNGConfig,
    ) {}

    ngAfterViewInit() {
        this.documentClickListener = this.renderer.listen(
            "body",
            "click",
            (event) => {
                if (!this.topbarItemClick) {
                    this.activeTopbarItem = null;
                    this.topbarMenuActive = false;
                }

                if (this.configActive && !this.configClick) {
                    this.configActive = false;
                }

                this.configClick = false;
                this.topbarItemClick = false;
                this.sidebarClick = false;
            }
        );
    }

    onTabClick(event: Event, index: number) {
        if (this.activeTabIndex === index) {
            this.sidebarActive = !this.sidebarActive;
        } else {
            this.activeTabIndex = index;
            this.sidebarActive = true;
        }

        event.preventDefault();
    }

    closeSidebar(event: Event) {
        this.sidebarActive = false;
        event.preventDefault();
    }

    onSidebarClick($event) {
        this.sidebarClick = true;
    }

    onTopbarMenuButtonClick(event) {
        this.topbarItemClick = true;
        this.topbarMenuActive = !this.topbarMenuActive;

        event.preventDefault();
    }

    onTopbarItemClick(event, item) {
        this.topbarItemClick = true;

        if (this.activeTopbarItem === item) {
            this.activeTopbarItem = null;
        } else {
            this.activeTopbarItem = item;
        }

        event.preventDefault();
    }

    onTopbarSubItemClick(event) {
        event.preventDefault();
    }

    onConfigClick(event) {
        this.configClick = true;
    }

    isDesktop() {
        return window.innerWidth > 1024;
    }

    ngOnDestroy() {
        if (this.documentClickListener) {
            this.documentClickListener();
        }
    }
}
