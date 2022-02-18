export abstract class StepMergeService<T> {
    private _state: T;
    private _index : number = 0;

    constructor(initialState: T) {
        if (initialState) {
            this._state = initialState;
        }
    }

    get state() {
        return this._state;
    }

    set index(value: number) {
        this._index = value;
    }

    get index() {
        return this._index;
    }

    set state(value: T) {
        this._state = value;
    }

    stepAnterior() {
        this._index--;
    }

    proximoStep() {
        this._index++;
    }

}
