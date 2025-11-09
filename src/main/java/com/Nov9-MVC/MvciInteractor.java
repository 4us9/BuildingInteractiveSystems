package com.Nov9-MVC;

class MvciInteractor {

    MvciModel viewModel;

    MvciInteractor(MvciModel viewModel) {
        this.viewModel = viewModel;
        viewModel.moreAllowedProperty()
            .bind(Bindings.createBooleanBinding(() -> checkIfMoreAllowed(),
                 viewModel.numberProperty()));
    }

    void addFive() {
        try {
            viewModel.setNumber(Integer.toString(Integer.parseInt(viewModel.getNumber()) + 5));
        } catch (NumberFormatException e) {
            viewModel.setNumber("5");
        }
    }

    private boolean checkIfMoreAllowed() {
        try {
            int numberValue = Integer.parseInt(viewModel.getNumber());
            return (numberValue < 21);
        } catch (Exception e) {
            return true;
        }
    }
}

